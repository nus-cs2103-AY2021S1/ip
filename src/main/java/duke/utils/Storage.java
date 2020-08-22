package duke.utils;

import duke.Messenger;
import duke.task.Task;
import duke.task.TaskList;
import duke.TaskType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

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
        this.taskList = list;
    }

    /**
     * Reads the saved file and populate the data into the task list.
     */
    public void readSavedFile() {
        try {
            Messenger.greet();
            System.out.println(Messenger.FILE_LOADING);
            File directory = new File(DIRECTORY_PATH);

            if (!directory.exists()) {
                System.out.println(Messenger.DIRECTORY_NOT_FOUND);
                directory.mkdir();
            }

            File f = new File(FILE_PATH);
            if (f.createNewFile()) {
                System.out.println(Messenger.FILE_NOT_FOUND);
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
                System.out.println(Messenger.FILE_LOADED);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the data in the task list into a saved file.
     */
    public void saveDataToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task task : taskList.getTasks()) {
                writer.write(task.getType() + "|" + task.getContent() +
                        (TaskType.hasTime(task.getType()) ? "|" + task.getDate() : "") + System.lineSeparator());
            }
            writer.close();
            Messenger.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
