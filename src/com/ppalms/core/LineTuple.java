package com.ppalms.core;

import java.util.ArrayList;

public class LineTuple {
    
    private ArrayList<Line> lines;
    private int number;
    private int start, end;

    public LineTuple(int start, int end, int number) {
        lines = new ArrayList<Line>();
        this.number = number;
        this.start = start;
        this.end = end;
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void removeLine(int index) {
        lines.remove(index);
    }

    public ArrayList<Line> getRawLines() {
        return lines;
    }

    public Line getLine(int index) {
        return lines.get(index);
    }

    public int getLineCount() {
        return lines.size();
    }

    public boolean isLineInside(int line) {
        return line >= start && line <= end;
    }

    public int getNumber() {
        return number;
    }

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
}
