package com.ppalms.core;

public class Instructor {
    
    private String name;
    private String role;
    private File activeFile;

    public Instructor(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public File getActiveFile() {
        return activeFile;
    }

    public void upload(String path) {
        
    }
}
