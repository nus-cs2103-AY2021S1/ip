package duke;

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

    private final File directory;
    private final File textFile;
    private final Ui ui;
    private final TaskList taskList;

    public Storage(String directoryPath, String textFilePath, Ui ui, TaskList taskList) {
        this.directory = new File(directoryPath);
        this.textFile = new File(textFilePath);
        this.ui = ui;
        this.taskList = taskList;
    }

    public Ui getUi() {
        return ui;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Checks if duke.txt file exists in the data directory of the root folder.
     * If it exists, load the data into Duke.
     * If it does not exist, create one.
     */
    public void checkSaveFile() {
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
            this.ui.printMessage("A data directory has been created in the root folder.");
        } else {
            this.ui.printWarning("Failed to create a data directory.");
        }
    }

    /**
     * Creates duke.txt in the data directory of the root folder.
     */
    private void createTextFile() {
        String failMessage = "Failed to create duke.txt.";
        try {
            boolean hasCreatedFile = textFile.createNewFile();
            if (hasCreatedFile) {
                this.ui.printMessage("duke.txt has been created in the data directory of the root folder.");
            } else {
                this.ui.printWarning(failMessage);
            }
        } catch (IOException e) {
            this.ui.printWarning(failMessage);
        }
    }

    /**
     * Reads duke.txt and adds tasks into the task list.
     */
    private void readTextFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(textFile));
            String line = reader.readLine();
            while (line != null) {
                String[] arr = line.split(" / ");
                Task task;
                boolean isDone = Integer.parseInt(arr[1]) != 0;
                if (arr[0].equals("T")) {    // Todo
                    task = new Todo(arr[2], isDone);
                } else if (arr[0].equals("E")) {    // Event
                    task = new Event(arr[2], isDone, arr[3]);
                } else {    // Deadline
                    task = new Deadline(arr[2], isDone, arr[3]);
                }
                this.taskList.addTask(task);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            this.ui.printWarning("Failed to read duke.txt.");
        }
    }

    /**
     * Writes tasks' data to duke.txt.
     */
    public void writeToSaveFile() {
        StringBuilder data = new StringBuilder();
        for (Task task: this.taskList.getTaskList()) {
            data.append(task.getData()).append("\n");
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
            writer.write(data.toString());
            writer.close();
        } catch (IOException e) {
            this.ui.printWarning("Failed to write data to duke.txt.");
        }
    }
}
