package duke.storage;

import duke.util.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Class encapsulating all the methods required to read and write directly to the hard drive.
 */
public class Storage {

    /** Path to search for the saved file to load or update */
    private String filePath;

    /** Private constructor to set the file path */
    private Storage(String filePath) {
        this.filePath = filePath;
    }

    /** Initialises the Storage object.
     * The method first attempts to search for a pre-saved list in a fixed location.
     * If it is not found, the method will create such a directory and file to write and save future updates.
     * @return Storage object for updating changes to the task list
     */
    public static Storage init() {
        String filePath = System.getProperty("user.dir");
        File directory = new File(filePath + "/data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        filePath = filePath + "/data/duke.txt";
        return new Storage(filePath);
    }

    /**
     * Reads the stored data in the file found in the file path.
     * It interprets the text found in the file to recreate the task list.
     * @return task list with items corresponding to what was found in the saved file or empty if a file was not found
     */
    public TaskList readStoredData() {
        File file = new File(filePath);
        try {
            Scanner scanner = new Scanner(file);
            TaskList taskList = new TaskList();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    taskList.add(createTask(line));
                } catch (InvalidSymbolException e) {
                    System.err.println("Not a valid line: " + line);
                    continue;
                }
            }
            return taskList;
        } catch (FileNotFoundException e1) {
            try {
                file.createNewFile();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return new TaskList();
        }
    }

    /**
     * Method recreates a task from the provided string summary of the task.
     * @param taskSummary string details of the task
     * @return task corresponding to the summary
     * @throws InvalidSymbolException if the symbol read is not a valid symbol of a task type
     */
    protected Task createTask(String taskSummary) throws InvalidSymbolException {
        String identifier = taskSummary.split("\\|", 2)[0];
        switch (identifier) {
        case Event.SYMBOL:
            return Event.reconstructFromSummary(taskSummary);
        case ToDo.SYMBOL:
            return ToDo.reconstructFromSummary(taskSummary);
        case Deadline.SYMBOL:
            return Deadline.reconstructFromSummary(taskSummary);
        default:
            throw new InvalidSymbolException(identifier + " is not a valid type symbol!");
        }
    }

    /**
     * Writes the new task list's details into the file at the file path.
     * @param taskList updated task list to be saved
     */
    public void updateFile(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            boolean isFirst = true;
            for (Task task : taskList) {
                if (isFirst) {
                    isFirst = false;
                    writer.write(task.getSummary());
                } else {
                    writer.write('\n' + task.getSummary());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
