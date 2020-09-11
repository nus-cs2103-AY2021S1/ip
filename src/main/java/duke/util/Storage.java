package duke.util;

import duke.Priority;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Encapsulates the loading of tasks from a text file and storing of tasks in that text file.
 * The text file is located in the data directory of the root folder.
 */
public class Storage {

    // Messages
    private static final String MSG_DIRECTORY_CREATED = "A data directory has been created in the root folder.";
    private static final String MSG_TEXT_FILE_CREATED =
            "duke.txt has been created in the data directory of the root folder.";
    private static final String WARN_DIRECTORY_NOT_CREATED = "Failed to create a data directory.";
    private static final String WARN_TEXT_FILE_NOT_CREATED = "Failed to create duke.txt.";
    private static final String WARN_TEXT_FILE_NOT_READ = "Failed to read duke.txt.";
    private static final String WARN_TEXT_FILE_NOT_WRITTEN = "Failed to write data to duke.txt.";

    /** Directory file. */
    private final File directory;

    /** Text file. */
    private final File textFile;

    /** UI for storage to display messages. */
    private final Ui ui;

    /** Task list for storage to access. */
    private final TaskList taskList;

    /**
     * Creates a storage for Duke's tasks data.
     *
     * @param directoryPath Path of the directory.
     * @param textFilePath Path of the text file.
     * @param ui UI.
     * @param taskList List of tasks.
     */
    public Storage(String directoryPath, String textFilePath, Ui ui, TaskList taskList) {
        this.directory = new File(directoryPath);
        this.textFile = new File(textFilePath);
        this.ui = ui;
        this.taskList = taskList;
    }

    /**
     * Gets the UI.
     *
     * @return UI.
     */
    public Ui getUi() {
        return ui;
    }

    /**
     * Gets the list of tasks.
     *
     * @return List of tasks.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Checks if duke.txt file exists in the data directory of the root folder.
     * If it exists, load the data into Duke.
     * If it does not exist, create one.
     */
    public void checkSavedFile() {
        if (directory.exists()) {
            if (textFile.exists()) {
                readTextFile();
            } else {
                createTextFile();
            }
        } else {
            createDataDirectory();
            createTextFile();
        }
    }

    /**
     * Creates a data directory in the root folder.
     */
    private void createDataDirectory() {
        boolean hasCreatedDir = directory.mkdir();
        if (hasCreatedDir) {
            this.ui.displayMessage(Storage.MSG_DIRECTORY_CREATED);
        } else {
            this.ui.displayWarning(Storage.WARN_DIRECTORY_NOT_CREATED);
        }
    }

    /**
     * Creates duke.txt in the data directory of the root folder.
     */
    private void createTextFile() {
        try {
            boolean hasCreatedFile = textFile.createNewFile();
            if (hasCreatedFile) {
                this.ui.displayMessage(Storage.MSG_TEXT_FILE_CREATED);
            } else {
                this.ui.displayWarning(Storage.WARN_TEXT_FILE_NOT_CREATED);
            }
        } catch (IOException e) {
            this.ui.displayWarning(Storage.WARN_TEXT_FILE_NOT_CREATED);
        }
    }

    /**
     * Parses a line read from duke.txt and adds tasks into the task list.
     *
     * @param line Line from duke.txt.
     */
    private void parseLine(String line) {
        Task task;
        String[] arr = line.split(" / ");

        Priority priority;
        String priorityLabel = arr[1];
        if (priorityLabel.equals("L")) {
            priority = Priority.LOW;
        } else if (priorityLabel.equals("M")) {
            priority = Priority.MEDIUM;
        } else {
            priority = Priority.HIGH;
        }

        boolean isDone = Integer.parseInt(arr[2]) != 0;
        if (arr[0].equals("T")) { // Todo
            task = new Todo(priority, arr[3], isDone);
        } else if (arr[0].equals("E")) { // Event
            task = new Event(priority, arr[3], isDone, arr[4]);
        } else { // Deadline
            task = new Deadline(priority, arr[3], isDone, arr[4]);
        }
        this.taskList.addTask(task);
    }

    /**
     * Reads duke.txt.
     */
    private void readTextFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(textFile));
            String line = reader.readLine();
            while (line != null) {
                this.parseLine(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            this.ui.displayWarning(Storage.WARN_TEXT_FILE_NOT_READ);
        }
    }

    /**
     * Writes tasks' data to duke.txt.
     */
    public void writeToSaveFile() {
        StringBuilder data = new StringBuilder();
        for (Task task : this.taskList.getTaskList()) {
            data.append(task.getData()).append("\n");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            this.ui.displayWarning(Storage.WARN_TEXT_FILE_NOT_WRITTEN);
        }
    }
}
