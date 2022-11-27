package com.ppalms.core;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.ppalms.utils.ConsoleColors;

public class File {
    
    private ArrayList<Line> lines;
    private ArrayList<LineTuple> tuples;
    private final String END_LINE = "\n";

    private boolean active = false;

    public File(String path) {
        lines = new ArrayList<Line>();
        tuples = new ArrayList<LineTuple>();

        Scanner scan;
        try {
            scan = new Scanner(new java.io.File(path));
            int position = 1;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                lines.add(new Line(position, false, line));
                position++;
            }
            active = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int createLineTuple() {
        LineTuple tuple = new LineTuple();
        tuples.add(tuple);

        return tuples.size() - 1;
    }

    public void removeLineTuple(int index) {
        tuples.remove(index);
    }

    public LineTuple getLineTuple(int index) {
        return tuples.get(index);
    }

    public void toggleCommented(int linePosition) {
        lines.get(linePosition - 1).setCommented(!lines.get(linePosition - 1).getIsComment());
    }

    public ArrayList<Line> getLines() {
        ArrayList<Line> output = new ArrayList<Line>();
        for(int i = 0; i < lines.size(); i++) {
            for(int j = 0; j < tuples.size(); j++) {
                if(tuples.get(j).toGroupedLine().getLinePosition() < lines.get(i).getLinePosition()) {
                    output.add(tuples.get(j).toGroupedLine());
                }
            }
            output.add(lines.get(i));
        }

        return output;
    }

    public boolean export(String path) {
        ArrayList<Line> myLines = getLines();
        try {
            FileWriter writer = new FileWriter(path);
            for(int i = 0; i < myLines.size(); i++) {
                writer.write(myLines.get(i).getLineContent() + END_LINE);
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public String toString() {
        String output = "";
        ArrayList<Line> myLines = getLines();
        for(int i = 0; i < myLines.size(); i++) {
            int position = myLines.get(i).getLinePosition();
            String lineNum = String.format("%03d", position);
            if(myLines.get(i).getIsComment())
                output += ConsoleColors.GREEN;

            output += lineNum + "  " + myLines.get(i).getLineContent() + END_LINE;

            if(myLines.get(i).getIsComment())
                output += ConsoleColors.RESET;
        }
        return output;
    }

}
