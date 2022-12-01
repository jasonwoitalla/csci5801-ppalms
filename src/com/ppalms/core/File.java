package com.ppalms.core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.ppalms.utils.ConsoleColors;

/**
 * This is the file class. It is a major class with a lot of logic of the PPALMS in it. It is a file
 * object that can be annotated with proper annotations and then used to generate questions.
 * 
 * @author Henry Samson, Jason Woitalla
 * @version 0.1
 * @since 2022-11-30
 */
public class File {
    
    private ArrayList<Line> lines;
    private ArrayList<LineTuple> tuples;
    private final String END_LINE = "\n";

    /**
     * The constructor for the file class. It takes in a file path and then reads the file and creates
     * a list of lines.
     * 
     * @param filePath The path to the file
     * @throws FileNotFoundException
     */
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to group lines together. It creates a line tuple object
     * that is stored in the file. It will remove the lines given from the lines
     * list and add them to the tuple. It will also add the tuple to the tuples list.
     * @param start The line number of the start of the tuple
     * @param end The line number of the end of the tuple
     * @return The index of the tuple created. Will return -1 if an invalid group was given.
     */
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

    /**
     * This method will remove the line tuple with the given group number. It
     * must add the lines from the tuple back into the files line list.
     * @param index The group number of the tuple
     */
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

    /**
     * This method is used to get a line at a given position from the file.
     * @param linePosition
     * @return The line at the given position
     */
    public Line getLine(int linePosition) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).getLinePosition() == linePosition) {
                return lines.get(i);
            }
        }
        return null;
    }

    /**
     * This method is used to get a line tuple at a given position from the file.
     * @param linePosition
     * @return The line tuple at the given position
     */
    public LineTuple getLineTuple(int index) {
        return tuples.get(index);
    }

    /**
     * This method will toggle the comment status of a line in the line group.
     * @param linePosition The line number of the line to toggle
     */
    public void toggleCommented(int linePosition) {
        for(int i = 0; i < lines.size(); i++) {
            if(lines.get(i).getLinePosition() == linePosition) {
                lines.get(i).setCommented(!lines.get(i).getIsComment());
                return;
            }
        }
    }

    /**
     * A method that gets the lines of the file. It will check to see if a line tuple group should
     * be inserted before the given line and insert it if that is true. By the end all lines and grouped lines
     * are in a single list. This method should be used rather than the raw lines list.
     * @return A special lines list that includes all lines and grouped lines.
     */
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

    /**
     * Gets all the line tuples
     * @return The line tuples
     */
    public ArrayList<LineTuple> getTuples(){
        return this.tuples;
    }

    /**
     * The to string method for printing the file.
     */
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
