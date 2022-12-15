package com.ppalms.test.core;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ppalms.core.Line;

public class LineTest {

    @Test
    public void testGetLinePosition() {
        Line test_line = new Line(2, true, "test, test");
        assertTrue("Line getLinePosition works: ", test_line.getLinePosition() == 2);
    }

    @Test
    public void testGetIsComment() {
        Line test_line = new Line(2, true, "test, test");
        assertTrue("Line getIsComment works: ", test_line.getIsComment());
    }

    @Test
    public void testSetCommented() {
        Line test_line = new Line(2, true, "test, test");
        test_line.setCommented(false);
        assertTrue("Line setCommented works: ", !test_line.getIsComment());
    }

    @Test
    public void testGetLineContent() {
        Line test_line = new Line(2, true, "test, test");
        assertTrue("Line getLineContent works: ", test_line.getLineContent().equals("test, test"));
    }

    @Test
    public void testSetGrouped() {
        Line test_line = new Line(2, true, "test, test");
        test_line.setGrouped(true);
        assertTrue("Line setGrouped works: ", test_line.getIsGrouped());
    }

    @Test
    public void testGetIsGrouped() {
        Line test_line = new Line(2, true, "test, test");
        test_line.setGrouped(false);
        assertTrue("Line getIsGrouped works: ", !test_line.getIsGrouped());
    }
}
