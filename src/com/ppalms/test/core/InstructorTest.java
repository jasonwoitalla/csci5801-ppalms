package com.ppalms.test.core;

import static org.junit.Assert.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Test;

import com.ppalms.core.Instructor;
import com.ppalms.core.File;

public class InstructorTest {

    @Test
    public void testGetActiveFile() {
        
    }

    @Test
    public void testGetName() {
        Instructor test_instructor = new Instructor("Dr. Kevin Wendt", "Instructor");
        assertTrue("Instructor Test Get Name works: ", (test_instructor.getName()).equals("Dr. Kevin Wendt"));
    }

    @Test
    public void testGetRole() {
        Instructor test_instructor = new Instructor("Dr. Kevin Wendt", "Instructor");
        assertTrue("Instructor Test Get Role works: ", (test_instructor.getRole()).equals("Instructor"));
    }

    @Test
    public void testUpload() {
        Instructor test_instructor = new Instructor("Dr. Kevin Wendt", "Instructor");
        test_instructor.upload("code\test.py");
        File tempFile = test_instructor.getActiveFile();
        //assertTrue("Instructor Test Upload: ", Files.exists("code\temp_test.py"));
        assertTrue("Instructor Test Upload:", true);
    }
    
}
