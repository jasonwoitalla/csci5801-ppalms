package com.ppalms.core;

public class Line {
    
    private int linePosition;
    private boolean isComment;
    private String content;

    public Line(int linePosition, boolean isComment, String content) {
        this.linePosition = linePosition;
        this.isComment = isComment;
        this.content = content;
    }

    public int getLinePosition() {
        return linePosition;
    }

    public boolean getIsComment() {
        return isComment;
    }

    public String getLineContent() {
        return content;
    }
}
