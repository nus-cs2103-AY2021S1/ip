package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.ArrayList;

import duke.exception.StorageException;

/**
 * Storage is a class that handles the interactions between the application and local storage.
 */
public class Storage {
    private final String filePath = "data/tasks.txt";
    private File storage;

    /**
     * Creates a Storage object.
     */
    public Storage() {
        this.storage = new File(filePath);
    }

    /**
     * Reads the lines of text in local storage and compiles them into an ArrayList of strings.
     * @return An ArrayList containing String representations of all the Tasks in local storage.
     * @throws StorageException if the storage cannot be accessed or read.
     */
    public ArrayList<String> readTaskStorage() throws StorageException {
        ArrayList<String> existingTasks = new ArrayList<>();
        try {
            if (this.storage.exists()) {
                // Load into taskList if file is not empty
                Scanner s = new Scanner(this.storage);
                if (this.storage.length() != 0) {
                    while (s.hasNext()) {
                        existingTasks.add(s.nextLine());
                    }
                }
            } else {
                this.storage.getParentFile().mkdirs();
                this.storage.createNewFile();
            }

            if (!this.storage.exists()) {
                throw new IOException();
            }
        } catch (IOException e) {
            throw new StorageException("Oh noes! I can't seem to find the tasks you saved previously ;A;");
        }
        return existingTasks;
    }

    /**
     * Appends a string representation of a Task to local storage.
     * @param taskString The String representation of a Task.
     * @throws StorageException if the storage cannot be accessed or read.
     */
    public void appendTaskStorage(String taskString) throws StorageException {
        try {
            FileWriter appender = new FileWriter(filePath, true);
            appender.write(taskString);
            appender.close();
        } catch (IOException e) {
            throw new StorageException("Oh noes! I can't seem to save this task ;A;");
        }
    }

    /**
     * Writes the string representations of all Tasks to local storage.
     * @param taskString The String representation of all tasks in the TaskList.
     * @throws StorageException if the storage cannot be accessed or read.
     */
    public void writeTaskStorage(String taskString) throws StorageException {
        try {
            FileWriter rewriter = new FileWriter(filePath);
            rewriter.write(taskString);
            rewriter.close();
        } catch (IOException e) {
            throw new StorageException("Oh noes! I can't seem to modify the tasks you saved previously ;A;");
        }
    }
}
