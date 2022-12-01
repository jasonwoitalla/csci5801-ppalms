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

    public int createLineTuple(int start, int end) {
        if(start > end || end-start < 1) {
            System.out.println("Invalid line range");
            return -1;
        }

        for(int i = 0; i < tuples.size(); i++) {
            if(tuples.get(i).isLineInside(start) || tuples.get(i).isLineInside(end)) {
                System.out.println("Line range overlaps with existing tuple");
                return -1;
            }
        }

        LineTuple tuple = new LineTuple(start, end, tuples.size());

        //Probably should change this to a hash map for efficiency but it doesn't matter right now.
        for (int i = start; i <= end; i++) {
            for (int j = 0; j < lines.size(); j++) {
                if (lines.get(j).getLinePosition() == i) {
                    tuple.addLine(lines.get(j));
                    lines.remove(j);
                    break;
                }
            }
        }
        
        tuples.add(tuple);
        return tuples.size() - 1;
    }

    public void removeLineTuple(int index) {
        for(int i = 0; i < tuples.size(); i++) {
            if(tuples.get(i).getNumber() == index) {
                System.out.println("Removing tuple: " + i);
                lines.addAll(tuples.get(i).getRawLines());
                tuples.remove(i);
                return;
            }
        }
    }

    public LineTuple getLineTuple(int index) {
        return tuples.get(index);
    }

    public void toggleCommented(int linePosition) {
        for(int i = 0; i < lines.size(); i++) {
            if(lines.get(i).getLinePosition() == linePosition) {
                lines.get(i).setCommented(!lines.get(i).getIsComment());
                return;
            }
        }
    }

    public ArrayList<Line> getLines() {
        lines.sort((l1, l2) -> l1.getLinePosition() - l2.getLinePosition()); // maintain sorted order

        ArrayList<Line> output = new ArrayList<Line>();
        ArrayList<LineTuple> myTuples = new ArrayList<LineTuple>(tuples);
        for(int i = 0; i < lines.size(); i++) {
            boolean tupleInserted = true;
            while(tupleInserted) {
                tupleInserted = false;
                for(int j = 0; j < myTuples.size(); j++) {
                    if(myTuples.get(j).toGroupedLine().getLinePosition() < lines.get(i).getLinePosition()) {
                        output.add(myTuples.remove(j).toGroupedLine());
                        tupleInserted = true;
                        break;
                    }
                }
            }
            output.add(lines.get(i));
        }

        myTuples.sort((l1, l2) -> l1.toGroupedLine().getLinePosition() < l2.toGroupedLine().getLinePosition() ? -1 : 1);
        for(int i = 0; i < myTuples.size(); i++) {
            output.add(myTuples.get(i).toGroupedLine());
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

    public ArrayList<LineTuple> getTuples(){
        return this.tuples;
    }

    public String toString() {
        String output = "";
        ArrayList<Line> myLines = getLines();
        for(int i = 0; i < myLines.size(); i++) {
            int position = myLines.get(i).getLinePosition();
            String lineNum = String.format("%03d", position);
            if(myLines.get(i).getIsComment())
                output += ConsoleColors.GREEN + "# ";
            if(myLines.get(i).getIsGrouped())
                output += ConsoleColors.YELLOW + "| ";

            output += lineNum + "  " + myLines.get(i).getLineContent() + END_LINE;

            if(myLines.get(i).getIsComment() || myLines.get(i).getIsGrouped())
                output += ConsoleColors.RESET;
        }
        return output;
    }

}
