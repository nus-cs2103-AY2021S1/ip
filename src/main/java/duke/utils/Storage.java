package duke.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import duke.components.AlertBox;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.types.TaskType;
import duke.views.Messenger;
import javafx.scene.control.Label;


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
    public void readSavedFile(Label label) {
        try {
            System.out.println(Messenger.FILE_LOADING);
            File directory = new File(DIRECTORY_PATH);

            if (!directory.exists()) {
                label.setText(Messenger.DIRECTORY_NOT_FOUND);
                directory.mkdir();
            }

            File f = new File(FILE_PATH);
            if (f.createNewFile()) {
                label.setText(Messenger.FILE_NOT_FOUND);
            } else {
                Scanner sc = new Scanner(f);
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] parsed = line.split("\\|");

                    String command = parsed[0];
                    if (TaskType.hasTime(command)) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                        LocalDate ld = LocalDate.parse(parsed[2], formatter);
                        taskList.getTasks().add(new Task(parsed[1], command, ld));
                    } else {
                        taskList.getTasks().add(new Task(parsed[1], command));
                    }
                }
                label.setText(Messenger.FILE_LOADED);
            }
        } catch (IOException e) {
            AlertBox.display("IO Error", e.getMessage());
        }
    }

    /**
     * Saves the data in the task list into a saved file.
     */
    public void saveDataToFile(Label label) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task task : taskList.getTasks()) {
                writer.write(task.getType() + "|" + task.getContent()
                        + (TaskType.hasTime(task.getType()) ? "|" + task.getDate() : "") + System.lineSeparator());
            }
            writer.close();
            taskList.clearTasks();
            label.setText(Messenger.CLOSE_MESSAGE);
        } catch (IOException e) {
            AlertBox.display("IO Error!", e.getMessage());
        }
    }
}
