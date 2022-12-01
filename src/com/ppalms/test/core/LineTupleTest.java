package com.ppalms.test.core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ppalms.core.Line;
import com.ppalms.core.LineTuple;

public class LineTupleTest {

    @Test
    public void testAddLine() {
        LineTuple test_lineTuple = new LineTuple(0, 1, 2);
        Line test_line = new Line(0, true, "test_comment");
        test_lineTuple.addLine(test_line);
        assertTrue("LineTuple addLine works: ", test_lineTuple.getRawLines().size() > 0);
    }
    @Test
    public void testRemoveLine() {
        LineTuple test_lineTuple = new LineTuple(0, 1, 2);
        Line test_line = new Line(0, true, "test_comment");
        test_lineTuple.addLine(test_line);
        test_lineTuple.removeLine(0);
        assertTrue("LineTuple removeLine works: ", test_lineTuple.getRawLines().size() == 0);
    }
    @Test
    public void testGetRawLines() {
        LineTuple test_lineTuple = new LineTuple(0, 1, 2);
        Line test_line = new Line(0, true, "test_comment");
        test_lineTuple.addLine(test_line);
    }

    @Test
    public void testGetLine() {
        LineTuple test_lineTuple = new LineTuple(0, 1, 2);
        Line test_line = new Line(0, true, "test_comment");
        test_lineTuple.addLine(test_line);
        assertTrue("LineTuple getLine works: ", test_lineTuple.getLine(0) == test_line);
    }

    @Test
    public void testToGroupedLine() {
        
    }
    
}
