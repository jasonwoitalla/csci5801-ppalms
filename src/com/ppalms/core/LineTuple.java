package com.ppalms.core;

import java.util.ArrayList;

public class LineTuple {
    
    private ArrayList<Line> lines;

    public LineTuple() {
        lines = new ArrayList<Line>();
    }

    public void addLine(Line line) {
        lines.add(line);
    }

    public void removeLine(int index) {
        lines.remove(index);
    }

    public Line getLine(int index) {
        return lines.get(index);
    }

    public Line toGroupedLine() {
        return null;
    }
}
