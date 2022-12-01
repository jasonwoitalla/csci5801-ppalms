package com.ppalms.core;

/**
 * This is the line class. It is an object that represents a line of code. It is used to keep track of the line number 
 * and the text of the line. It is here to make several annotation options easier than working with strings.
 * 
 * @author Henry Samson, Jason Woitalla
 * @version 0.1
 * @since 2022-11-30
 */
public class Line {
    
    private int linePosition;
    private boolean isComment;
    private String content;
    private boolean isGrouped;

    /**
     * The constructor for the line class. It takes in a line position, a boolean for if it is a comment, and the content of the line.
     * @param linePosition The line number of the line in the file
     * @param isComment A boolean for if the line is a comment
     * @param content The content of the line
     */
    public Line(int linePosition, boolean isComment, String content) {
        this.linePosition = linePosition;
        this.isComment = isComment;
        this.content = content;
    }

    /**
     * This method is used to get the line position of the line
     * @return The line position of the line
     */
    public int getLinePosition() {
        return linePosition;
    }

    /**
     * This method is used to get if the line is a comment
     * @return A boolean for if the line is a comment
     */
    public boolean getIsComment() {
        return isComment;
    }

    /**
     * This method is used to set if this line is a comment
     * @param isComment A boolean for if the line is a comment
     */
    public void setCommented(boolean isComment) {
        this.isComment = isComment;
    }

    /**
     * This method is used to get the content of the line
     * @return The content of the line
     */
    public String getLineContent() {
        return content;
    }

    /**
     * This method is used to set if this line is apart of a group
     * @param isGrouped A boolean for if the line is grouped
     */
    public void setGrouped(boolean isGrouped) {
        this.isGrouped = isGrouped;
    }

    /**
     * This method is used to get if the line is a group line or a normal line
     * @return A boolean for if the line is grouped
     */
    public boolean getIsGrouped() {
        return isGrouped;
    }

    /**
     * The equals method for the line class. It checks if the line position and content are the same.
     */
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Line) || o == null) {
            return false;
        }
        Line line = (Line) o;
        return linePosition == line.linePosition && isComment == line.isComment && content.equals(line.content);
    }

    /**
     * The toString method for the line class. It returns the content of the line.
     */
    public String toString() {
        return "Line: " + linePosition + " " + content;
    }
}
