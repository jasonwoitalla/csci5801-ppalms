package com.ppalms.test.core;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

import com.ppalms.core.Driver;

public class DriverTest {
    
    private final String absoluteFilePath = "C:/Users/jason/Documents/School/Fall 2022/CSCI 5801/csci5801-ppalms/code/test.py";

    private String[] outputUploadLines = new String[]{
        "Welcome to the PPALMS.",
        "You are now logged in as an instructor.",
        "Please upload a file to begin:",
        "Instructor is uploading a file located at: " + absoluteFilePath,
        "File uploaded successfully.",
        "001  # This is the foo function",
        "002  def foo():",
        "003      x = 5",
        "004      y = 6",
        "005",
        "006      # find their sum",
        "007      sum = x + y",
        "008      if sum > 10:",
        "009          print(\"sum is greater than 10\")",
        "010      else:",
        "011          print(\"sum is less than 10\")",
        "",
        "Please select the question type you would like to generate",
        "Options:",
        "0: DEFAULT",
        "1: MULTIPLE_CHOICE",
        "2: FILL_IN_BLANK",
        "Selection: ",
        "You have selected to generate DEFAULT questions",
        "Enter a command with a line number(s) to annotate your file.",
        "Comment: c <line number>",
        "Create tuple: t <start line> <end line>",
        "Remove tuple: r <tuple number>",
        "Generate questions: g (NOT IMPLEMENTED IN THIS VERSION)",
        "Enter your command: "
    };

    @Test
    public void testUserUpload() {
        InputStream stdin = System.in; 
        System.setIn(new ByteArrayInputStream((absoluteFilePath + "\n0\nq\n").getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outContent);
        PrintStream stdout = System.out;
        System.setOut(ps);

        Driver.main(null);

        System.setIn(stdin); // reset System.in to its original
        System.setOut(stdout); // reset System.out to its original

        String outputString = outContent.toString();
        for(String line : outputUploadLines) {
            assertTrue("Console has line: " + line, outputString.contains(line));
        }
    }

    private String[] outputCommentLines = new String[]{
        "Welcome to the PPALMS.",
        "You are now logged in as an instructor.",
        "Please upload a file to begin:",
        "Instructor is uploading a file located at: " + absoluteFilePath,
        "File uploaded successfully.",
        "001  # This is the foo function",
        "002  def foo():",
        "003      x = 5",
        "004      y = 6",
        "005",
        "006      # find their sum",
        "007      sum = x + y",
        "008      if sum > 10:",
        "009          print(\"sum is greater than 10\")",
        "010      else:",
        "011          print(\"sum is less than 10\")",
        "",
        "Please select the question type you would like to generate",
        "Options:",
        "0: DEFAULT",
        "1: MULTIPLE_CHOICE",
        "2: FILL_IN_BLANK",
        "Selection: ",
        "You have selected to generate DEFAULT questions",
        "Enter a command with a line number(s) to annotate your file.",
        "Comment: c <line number>",
        "Create tuple: t <start line> <end line>",
        "Remove tuple: r <tuple number>",
        "Generate questions: g (NOT IMPLEMENTED IN THIS VERSION)",
        "Enter your command: ",
        "# 001  # This is the foo function",
        "002  def foo():",
        "003      x = 5",
        "004      y = 6",
        "005",
        "006      # find their sum",
        "007      sum = x + y",
        "008      if sum > 10:",
        "009          print(\"sum is greater than 10\")",
        "010      else:",
        "011          print(\"sum is less than 10\")",
        "",
        "Enter your command: ",
        "001  # This is the foo function",
        "002  def foo():",
        "003      x = 5",
        "004      y = 6",
        "005",
        "006      # find their sum",
        "007      sum = x + y",
        "008      if sum > 10:",
        "009          print(\"sum is greater than 10\")",
        "010      else:",
        "011          print(\"sum is less than 10\")",
        "",
        "Enter your command: ",
        "001  # This is the foo function",
        "# 002  def foo():",
        "003      x = 5",
        "004      y = 6",
        "005",
        "006      # find their sum",
        "007      sum = x + y",
        "008      if sum > 10:",
        "009          print(\"sum is greater than 10\")",
        "010      else:",
        "011          print(\"sum is less than 10\")",
        "",
        "Enter your command: "
    };

    private final String commentInput = absoluteFilePath + "\n0\nc 1\nc 1\nc 2\nq\n";

    @Test
    public void testUserComment() {
        InputStream stdin = System.in; 
        System.setIn(new ByteArrayInputStream((commentInput).getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outContent);
        PrintStream stdout = System.out;
        System.setOut(ps);

        Driver.main(null);

        System.setIn(stdin); // reset System.in to its original
        System.setOut(stdout); // reset System.out to its original

        String outputString = outContent.toString();
        for(String line : outputCommentLines) {
            assertTrue("Console has line: " + outputString, outputString.contains(line));
        }
    }
}
