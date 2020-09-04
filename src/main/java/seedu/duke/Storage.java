package seedu.duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.util.Scanner;

import seedu.duke.task.Task;

/**
 * Represents a file on which the user's tasks are stored.
 */
public class Storage {
    private String filePath;
    private File file;

    /**
     * Class constructor.
     *
     * @param filePath directory and name of the file to save the user's tasks to
     * @throws DukeException if there is a problem when creating a new file on the user's system
     */
    Storage(String filePath) throws DukeException {
        int i = filePath.lastIndexOf("/");
        if (i != -1) {
            String directory = filePath.substring(0, i);
            new File(directory).mkdirs();
        }
        this.filePath = filePath;
        this.file = new File(filePath);
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Problem reading file.");
        }
    }

    /**
     * Loads tasks from a file into the <code>TaskList</code> object.
     *
     * @return a list of tasks previously saved by the user
     * @throws DukeException if tasks cannot be read from the file correctly
     */
    public TaskList readTasks() throws DukeException {
        TaskList taskList = new TaskList();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                taskList.add(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DateTimeException e) {
            System.out.println("Problem reading file.");
        }
        return taskList;
    }

    /**
     * Writes the list of tasks into a file from the start.
     *
     * @param taskList the user's current list of tasks
     */
    public void writeToFile(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            taskList.forEach(task -> {
                try {
                    fileWriter.write(task.print() + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends a task to the back of a file.
     *
     * @param task the new task to be saved
     */
    public void appendToFile(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath, true);
            fileWriter.write(task.print() + System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
