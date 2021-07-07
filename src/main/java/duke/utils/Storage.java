package duke.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.types.TaskType;
import duke.ui.AlertBox;
import duke.ui.Messenger;


/**
 * Encapsulates a storage class that takes charge of loading saved files and saving data to file.
 */
public class Storage {
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/duke.txt";

    private TaskList taskList;

    /**
     * Constructs a storage object with a task list.
     *
     * @param list a task list for the storage object to populate with data.
     */
    public Storage(TaskList list) {
        taskList = list;
    }

    /**
     * Gets the task list of the storage object.
     *
     * @return a TaskList object representing the task list in the storage.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Reads the saved file and populate the data into the task list.
     */
    public String readSavedFile() {
        String output = "";
        try {
            output += Messenger.FILE_LOADING + "\n";
            File directory = new File(DIRECTORY_PATH);

            if (!directory.exists()) {
                output += Messenger.DIRECTORY_NOT_FOUND + "\n";
                directory.mkdir();
            }
            assert directory.exists() : "directory does not exist";

            File f = new File(FILE_PATH);
            if (f.createNewFile()) {
                output += Messenger.FILE_NOT_FOUND + "\n";
                return output;
            }

            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parsed = line.split("\\|");

                String command = parsed[0];
                if (TaskType.hasTime(command)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                    LocalDate ld = LocalDate.parse(parsed[2], formatter);
                    taskList.getTasks().add(new Task(parsed[1], command, ld));
                    continue;
                }
                taskList.getTasks().add(new Task(parsed[1], command));
            }
            output += Messenger.FILE_LOADED + "\n";
            assert f.exists() : "file does not exist";
        } catch (IOException e) {
            AlertBox.display("IO Error", e.getMessage());
        }

        return output;
    }

    /**
     * Saves the data in the task list into a saved file.
     *
     * @return a string representing the message for saving data to file.
     */
    public String saveDataToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task task : taskList.getTasks()) {
                writer.write(parseSavedFileFormat(task));
            }
            writer.close();
            taskList.clearTasks();
            assert taskList.getTasks().size() == 0 : "task list is not cleared";
            return Messenger.CLOSE_MESSAGE;
        } catch (IOException e) {
            String errorTitle = "IO Error!";
            AlertBox.display(errorTitle, e.getMessage());
            return errorTitle;
        }
    }

    private String parseSavedFileFormat(Task task) {
        return task.getType() + "|" + task.getContent()
                + (TaskType.hasTime(task.getType())
                    ? "|" + task.getDate()
                    : "") + System.lineSeparator();
    }
}
