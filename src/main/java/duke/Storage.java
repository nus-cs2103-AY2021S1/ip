package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
            }

            assert this.storage.exists() : "Local storage not found";
        } catch (IOException e) {
            System.out.println("Irrecoverable error");
        }
    }

    /**
     * Reads the lines of text in local storage and compiles them into an ArrayList of strings.
     * @return An ArrayList containing String representations of all the Tasks in local storage.
     */
    public ArrayList<String> readTaskStorage() {
        ArrayList<String> existingTasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(this.storage);
            while (s.hasNext()) {
                existingTasks.add(s.nextLine());
            }
        } catch (IOException e) {
            assert false : "Local storage cannot be read";
        }
        return existingTasks;
    }

    /**
     * Appends a string representation of a Task to local storage.
     * If an IO Exception is thrown, it cannot be recovered from, so the program terminates.
     * @param taskString The String representation of a Task.
     */
    public void appendTaskStorage(String taskString) {
        try {
            FileWriter appender = new FileWriter(FILEPATH, true);
            appender.write(taskString);
            appender.close();
        } catch (IOException e) {
            assert false : "Task cannot be saved";
        }
    }

    /**
     * Writes the string representations of all Tasks to local storage.
     * If an IO Exception is thrown, it cannot be recovered from, so the program terminates.
     * @param taskString The String representation of all tasks in the TaskList.
     */
    public void writeToTaskStorage(String taskString) {
        try {
            FileWriter rewriter = new FileWriter(FILEPATH);
            rewriter.write(taskString);
            rewriter.close();
        } catch (IOException e) {
            assert false : "Tasks cannot be saved";
        }
    }
}
