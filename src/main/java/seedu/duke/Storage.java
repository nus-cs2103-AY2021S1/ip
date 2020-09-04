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
     * @throws IOException if there is a problem when creating a new file on the user's system
     */
    Storage(String filePath) throws IOException {
        int i = filePath.lastIndexOf("/");
        if (i != -1) {
            String directory = filePath.substring(0, i);
            new File(directory).mkdirs();
        }
        this.filePath = filePath;
        this.file = new File(filePath);
        this.file.createNewFile();
    }

    /**
     * Loads tasks from a file into the <code>TaskList</code> object.
     *
     * @return a list of tasks previously saved by the user
     * @throws IOException if tasks cannot be read from the file correctly
     */
    public TaskList readTasks() throws IOException {
        TaskList taskList = new TaskList();
        try {
            Scanner scanner = new Scanner(this.file);
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                taskList.add(input);
            }
        } catch (DateTimeException e) {
            throw new IOException("Dates in file could not be read properly.");
        }
        return taskList;
    }

    /**
     * Writes the list of tasks into a file from the start.
     *
     * @param taskList the user's current list of tasks
     */
    public void writeToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        taskList.forEach(task -> {
            try {
                fileWriter.write(task.print() + System.lineSeparator());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        fileWriter.close();
    }

    /**
     * Appends a task to the back of a file.
     *
     * @param task the new task to be saved
     */
    public void appendToFile(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath, true);
        fileWriter.write(task.print() + System.lineSeparator());
        fileWriter.close();
    }
}
