package com.ppalms.core;

/**
 * This is the instructor class. It is used to keep track of what user does what with the system. 
 * This class exists to fit a number of requirements the biggest one is the user management subsystem
 * and the ability to load in older files into the system. (See design doc for more details)
 * 
 * @author Henry Samson, Jason Woitalla
 * @version 0.1
 * @since 2022-11-30
 */
public class Instructor {
    
    private String name;
    private String role;
    private File activeFile;

    /**
     * This is the constructor for the instructor class. It takes in a name and a role and sets them
     * @param name The name of the instructor
     * @param role The role of the instructor
     */
    public Instructor(String name, String role) {
        this.name = name;
        this.role = role;
    }

    /**
     * This method is used to get the name of the instructor
     * @return The name of the instructor
     */
    public String getName() {
        return name;
    }

    /**
     * This method is used to get the role of the instructor
     * @return The role of the instructor
     */
    public String getRole() {
        return role;
    }

    /**
     * This method is used to upload a file to the system. It takes in a path to the file and then creates a new file object
     * @param path The path to the file
     */
    public File getActiveFile() {
        return activeFile;
    }

    /**
     * This method is used to upload an active file into the system.
     * @param path The path of the file location
     */
    public void upload(String path) {
        System.out.println("Instructor is uploading a file located at: " + path);
        File file = new File(path);
        activeFile = file;
    }
}
