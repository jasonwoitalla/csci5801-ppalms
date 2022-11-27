package com.ppalms.core;

import java.util.Scanner;

public class Driver {
    
    public static void main(String[] args) {
        System.out.println("Welcome to the PPALMS.");
        System.out.println("You are now logged in as an instructor.");
        System.out.print("Please upload a file to begin: ");

        Instructor instructor = new Instructor("Admin", "Instructor");
        Scanner scan = new Scanner(System.in);

        String path = scan.nextLine();
        instructor.upload(path);

        System.out.println("File uploaded successfully.");
        printFile(instructor.getActiveFile());
        System.out.println("Enter a command with a line number(s) to annotate your file.");
        System.out.println("Comment: c <line number>");
        System.out.println("Create tuple: t <line numbers>");
        System.out.println("Remove tuple: r <tuple number>");

        int status = 0;
        while(status >= 0) {
            System.out.print("Enter your command: ");
            String command = scan.nextLine();
            char action = command.charAt(0);
            String[] cmdArgs = command.substring(2).split(" ");
            int[] argsInt = new int[cmdArgs.length];
            for(int i = 0; i < cmdArgs.length; i++) {
                argsInt[i] = Integer.parseInt(cmdArgs[i]);
            }
            switch(action) {
                case 'c':
                    instructor.getActiveFile().toggleCommented(argsInt[0]);
                    break;
                case 't':
                    int tupleIndex = instructor.getActiveFile().createLineTuple();
                    for(int i = 0; i < argsInt.length; i++) {
                        int linePosition = argsInt[i] - 1;
                        Line myLine = instructor.getActiveFile().getLines().get(linePosition);
                        instructor.getActiveFile().getLineTuple(tupleIndex).addLine(myLine);
                    }
                    break;
                case 'r':
                    instructor.getActiveFile().removeLineTuple(argsInt[0] - 1);
                    break;
                case 'q':
                    status = -1;
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
            printFile(instructor.getActiveFile());
        }
    }

    private static void printFile(File file) {
        System.out.println(file);
    }
}
