package com.ppalms.test.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.ppalms.core.LineTuple;
import com.ppalms.core.File;

public class FileTest {

    /**
     * Tests that I can think of:
     * Comment and then uncomment a line
     * Comment a line and then create a line tuple underneath it
     * Comment a line and then create a line tuple above it
     * Comment a line and then create a line tuple above and below it
     * Create a line tuple and then comment a line above it
     * Create a line tuple and then comment a line below it
     * Create a line tuple and then remove it
     */

    @Test
    public void testCreateLineTuple() {
        File test_file = new File("code\test_file.py");
        LineTuple test_lineTuple = new LineTuple(0, 0, 0);
        int x = test_file.createLineTuple(0, 0);
        ArrayList<LineTuple> test_lineTuple_array = new ArrayList<LineTuple>();
        test_lineTuple_array.add(test_lineTuple);
        assertTrue("File createLineTuple works: ", test_file.getTuples() == test_lineTuple_array);
    }

    @Test
    public void testGetLineTuple() {
        File test_file = new File("code\test_file.py");
        int x = test_file.createLineTuple(0, 0);
        LineTuple test_lineTuple = new LineTuple(0, 0, 0);
        assertTrue("File getLineTuple works: ", test_file.getLineTuple(0) == test_lineTuple);
    }

    @Test
    public void testRemoveLineTuple() {
        File test_file = new File("code\test_file.py");
        int x = test_file.createLineTuple(0, 0);
        test_file.removeLineTuple(0);
        assertTrue("File removeLineTuple works: ", test_file.getTuples().size() == 0);
    }
    
}
