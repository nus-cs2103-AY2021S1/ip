package com.siawsam.duke;

import java.io.IOException;

/**
 * Represents a class that handles disk operations for Duke.
 */
public class Storage {
    /** The file path associated with the current running instance of Duke. */
    private final String filePath;

    /**
     * Constructs a new Storage instance with an associated file path.
     *
     * @param filePath The file path associated with a running instance of Duke.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads a TaskList instance from disk.
     *
     * @return The saved TaskList.
     * @throws IOException if an IO exception occurs while reading.
     * @throws ClassNotFoundException if the file cannot be deserialized into a TaskList.
     */
    TaskList load() throws IOException, ClassNotFoundException {
        return Reader.readListFromFile(this.filePath);
    }

    /**
     * Saves a TaskList instance to disk.
     *
     * @param taskList The TaskList instance to save.
     */
    void save(TaskList taskList) {
        Writer.writeListToFile(taskList, filePath);
    }

    /**
     * Checks if a file already exists at the storage file path.
     *
     * @return True if file exists, False if otherwise.
     */
    boolean doesExist() {
        return Reader.doesFileExist(this.filePath);
    }

}
