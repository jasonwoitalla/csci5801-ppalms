package com.ppalms.core;

import java.util.ArrayList;

public class File {
    
    private ArrayList<Line> lines;
    private ArrayList<LineTuple> tuples;

    public File(String path) {
        lines = new ArrayList<Line>();
        tuples = new ArrayList<LineTuple>();
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

    public boolean export(String path) {
        return false;
    }

}
