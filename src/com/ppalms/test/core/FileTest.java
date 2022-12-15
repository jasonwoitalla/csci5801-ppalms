package com.ppalms.test.core;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.ppalms.core.LineTuple;
import com.ppalms.core.File;

public class FileTest {

    private final String absoluteFilePath = "C:/Users/jason/Documents/School/Fall 2022/CSCI 5801/csci5801-ppalms/code/test.py";

    @Test
    public void testCreateBlankLineTuple() {
        File testFile = new File(absoluteFilePath);
        int x = testFile.createLineTuple(0, 0);
        
        ArrayList<LineTuple> test_lineTuple_array = new ArrayList<LineTuple>();
        LineTuple test_lineTuple = new LineTuple(0, 0, 0);
        test_lineTuple_array.add(test_lineTuple);
        assertTrue("Testing that we can't create blank line tuples: ", x == -1);
    }

    @Test
    public void testCreateFlippedLineTuple() {
        File test_file = new File(absoluteFilePath);
        int x = test_file.createLineTuple(1, 0);
        
        ArrayList<LineTuple> test_lineTuple_array = new ArrayList<LineTuple>();
        LineTuple test_lineTuple = new LineTuple(0, 0, 0);
        test_lineTuple_array.add(test_lineTuple);
        assertTrue("Testing that we can't backwards line tuples: ", x == -1);
    }

    @Test
    public void testCreateSmallLineTuple() {
        File test_file = new File(absoluteFilePath);
        LineTuple test_lineTuple = new LineTuple(1, 2, 0);
        test_lineTuple.addLine(test_file.getLine(1));
        test_lineTuple.addLine(test_file.getLine(2));
        
        test_file.createLineTuple(1, 2);
        assertTrue("File createLine works: ", test_file.getLineTuple(0).equals(test_lineTuple));
    }

    @Test
    public void testGetLineTuple() {
        File test_file = new File(absoluteFilePath);
        LineTuple test_lineTuple = new LineTuple(1, 2, 0);
        test_lineTuple.addLine(test_file.getLine(1));
        test_lineTuple.addLine(test_file.getLine(2));
        
        test_file.createLineTuple(1, 2);
        assertTrue("File getLineTuple works: ", test_file.getLineTuple(0).equals(test_lineTuple));
    }

    @Test
    public void testOverlappingLineTuple() {
        File test_file = new File(absoluteFilePath);
        test_file.createLineTuple(1, 3);
        int x = test_file.createLineTuple(2, 4);
        assertTrue("File getLineTuple works: ", x == -1);
    }

    @Test
    public void testRemoveLineTuple() {
        File test_file = new File(absoluteFilePath);
        int x = test_file.createLineTuple(0, 0);
        test_file.removeLineTuple(0);
        assertTrue("File removeLineTuple works: ", test_file.getTuples().size() == 0);
    }
    
}
