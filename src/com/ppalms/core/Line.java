package com.ppalms.core;

public class Line {
    
    private int linePosition;
    private boolean isComment;
    private String content;
    private boolean isGrouped;

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

    public void setCommented(boolean isComment) {
        this.isComment = isComment;
    }

    public String getLineContent() {
        return content;
    }

    public void setGrouped(boolean isGrouped) {
        this.isGrouped = isGrouped;
    }

    public boolean getIsGrouped() {
        return isGrouped;
    }
}
