package com.ppalms.core;

import java.util.ArrayList;

/**
 * This is the line tuple class. It is used to group lines together. It stores a list of lines and when asked can return a single
 * line object that has the content of all the lines. This is powerful as that single line can be reinserted into the file for 
 * question generation and all the lines in the group will stay grouped. It also contains extra information such as the start and
 * end line numbers and the line tuple number.
 * 
 * @author Henry Samson, Jason Woitalla
 * @version 0.1
 * @since 2022-11-30
 */
public class LineTuple {
    
    private ArrayList<Line> lines;
    private int number;
    private int start, end;

    /**
     * The constructor for the LineTuple class. It initializes the lines arraylist and sets the number of the tuple.
     * 
     * @param start The starting line of the tuple
     * @param end The ending line of the tuple
     * @param number The group number of the tuple
     */
    public LineTuple(int start, int end, int number) {
        lines = new ArrayList<Line>();
        this.number = number;
        this.start = start;
        this.end = end;
    }

    /**
     * This method is used to add a line to the tuple
     * @param line The line to add to the tuple
     */
    public void addLine(Line line) {
        lines.add(line);
    }

    /**
     * This method is used to remove a line from the tuple
     * @param index The index of the line to remove
     */
    public void removeLine(int index) {
        lines.remove(index);
    }

    /**
     * This method is used to get the lines in the tuple
     * @return The lines in the tuple
     */
    public ArrayList<Line> getRawLines() {
        return lines;
    }

    /**
     * Gets a line at the given index in the tuple
     * @param index the index of the line to get
     * @return The line at the index
     */
    public Line getLine(int index) {
        return lines.get(index);
    }

    /**
     * Gets how many lines are in this tuple
     * @return The number of lines in the tuple
     */
    public int getLineCount() {
        return lines.size();
    }

    /**
     * This method is used to tell if a line is inside this tuple quickly.
     * @param line The line to check
     * @return A boolean for if the line is in the tuple
     */
    public boolean isLineInside(int line) {
        return line >= start && line <= end;
    }

    /**
     * This method is used to get the group number of the tuple
     * @return The group number of the tuple
     */
    public int getNumber() {
        return number;
    }

    /**
     * This is the main reason for this class. A method to get all the lines in the tuple and treat them
     * as a single line for annotation. This way all the lines in the group stay together no matter what.
     * @return A line object that has the content of all the liens in the group.
     */
    public Line toGroupedLine() {
        String content = "";
        int smallestPosition = lines.get(0).getLinePosition();
        for (int i = 0; i < lines.size(); i++) {
            content += lines.get(i).getLineContent();
            if (i != lines.size() - 1) {
                String groupNum = String.format("G%02d", number);
                content += "\n| " + groupNum + "  ";
            }
            if(smallestPosition > lines.get(i).getLinePosition()) {
                smallestPosition = lines.get(i).getLinePosition();
            }
        }
        Line output = new Line(smallestPosition, false, content);
        output.setGrouped(true);

        return output;
    }

    /**
     * The toString method. It is used to print the tuple.
     */
    @Override
    public String toString() {
        return "LineTuple [lines=" + lines + ", number=" + number + ", start=" + start + ", end=" + end + "]";
    }

    /** 
     * The equals method for line tuples. It checks if the line tuples are equal by checking if the start and end lines are the same.
     */
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if(!(o instanceof LineTuple) || o == null) {
            return false;
        }
        LineTuple other = (LineTuple) o;
        return other.start == start && other.end == end && other.number == number && other.lines.equals(lines);
    }
}
