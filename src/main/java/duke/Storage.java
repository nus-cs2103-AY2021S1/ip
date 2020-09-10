package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.StorageException;

/**
 * Storage is a class that handles the interactions between the application and local storage.
 */
public class Storage {
    private static final String FILEPATH = "data/tasks.txt";
    private File storage;

    /**
     * Creates a Storage object.
     */
    public Storage() {
        this.storage = new File(FILEPATH);
        try {
            if (!this.storage.exists()) {
                this.storage.getParentFile().mkdirs();
                this.storage.createNewFile();

                if (!this.storage.exists()) {
                    throw new IOException();
                }
            }
        } catch (Exception e) {
            System.out.println("Irrecoverable error");
        }
    }

    /**
     * Reads the lines of text in local storage and compiles them into an ArrayList of strings.
     * @return An ArrayList containing String representations of all the Tasks in local storage.
     * @throws StorageException If the storage cannot be accessed or read.
     */
    public ArrayList<String> readTaskStorage() throws StorageException {
        ArrayList<String> existingTasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(this.storage);
            while (s.hasNext()) {
                existingTasks.add(s.nextLine());
            }
        } catch (IOException e) {
            throw new StorageException("Oh noes! I can't seem to find the tasks you saved previously ;A;");
        }
        return existingTasks;
    }

    /**
     * Appends a string representation of a Task to local storage.
     * @param taskString The String representation of a Task.
     * @throws StorageException If the storage cannot be accessed or read.
     */
    public void appendTaskStorage(String taskString) throws StorageException {
        try {
            FileWriter appender = new FileWriter(FILEPATH, true);
            appender.write(taskString);
            appender.close();
        } catch (IOException e) {
            throw new StorageException("Oh noes! I can't seem to save this task ;A;");
        }
    }

    /**
     * Writes the string representations of all Tasks to local storage.
     * @param taskString The String representation of all tasks in the TaskList.
     * @throws StorageException If the storage cannot be accessed or read.
     */
    public void writeToTaskStorage(String taskString) throws StorageException {
        try {
            FileWriter rewriter = new FileWriter(FILEPATH);
            rewriter.write(taskString);
            rewriter.close();
        } catch (IOException e) {
            throw new StorageException("Oh noes! I can't seem to modify the tasks you saved previously ;A;");
        }
    }
}
