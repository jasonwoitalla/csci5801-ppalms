package com.ppalms.core;

import java.util.Scanner;

import com.ppalms.core.Instructor.QuestionType;

/**
 * The driver class. Read the readme documentation to learn more about the program.
 * It will explain how to use this class. This class is not apart of the PPALMS api. 
 */
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
        System.out.println("Please select the question type you would like to generate");
        System.out.println("Options: ");
        for(int i = 0; i < Instructor.QuestionType.values().length; i++) {
            System.out.println(i + ": " + Instructor.QuestionType.values()[i]);
        }
        System.out.print("Selection: ");
        int questionType = scan.nextInt();
        scan.nextLine();
        instructor.setQuestionType(QuestionType.values()[questionType]);
        System.out.println("You have selected to generate " + instructor.getQuestionType() + " questions");

        System.out.println("Enter a command with a line number(s) to annotate your file.");
        System.out.println("Comment: c <line number>");
        System.out.println("Create tuple: t <start line> <end line>");
        System.out.println("Remove tuple: r <tuple number>");
        System.out.println("Generate questions: g (NOT IMPLEMENTED IN THIS VERSION)");

        int status = 0;
        while(status >= 0) {
            System.out.print("Enter your command: ");
            String command = scan.nextLine();
            char action = command.charAt(0);
            if(action == 'q') {
                scan.close();
                return;
            }

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
                    int index = instructor.getActiveFile().createLineTuple(argsInt[0], argsInt[1]);
                    if(index == -1) {
                        System.out.println("Invalid grouping.");
                    }
                    break;
                case 'r':
                    instructor.getActiveFile().removeLineTuple(argsInt[0]);
                    break;
                case 'q':
                    status = -1;
                    break;
                case 'g':
                    System.out.println("This feature is not implemented in this version.");
                    System.out.println("When it is your annotations will be good enough for question generation.");
                    status = -1;
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
            printFile(instructor.getActiveFile());
        }

        scan.close();
    }

    private static void printFile(File file) {
        System.out.println(file);
    }
}
