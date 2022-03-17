package duke;

import java.util.Scanner;

import java.util.ArrayList;

import duke.exception.DukeException;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles loading tasks from the given file and saving tasks to the same file.
 */
public class Storage {
    
    private final String filePath;

    public static final String STORAGE_FILEPATH = "duke.txt";

    /**
     * Constructs a <code>Storage</code> Object using filePath.
     *
     * @param filePath The filePath where the data is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Marks task as done if {@code taskStatusCode} has status code of 1.
     */
    private void markTaskDone(Task task, String taskStatusCode) throws DukeException {
        if (taskStatusCode.equals("1")) {
            task.markAsDone();
        }
    }

    /**
     * Loads the existing tasks from the data file.
     *
     * @return An ArrayList of tasks which are stored in the data file.
     * @throws DukeException If file does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(" \\| ");
                Task task;

                switch (data[0]) {
                case "T":
                    task = new ToDo(data[3]);
                    break;
                case "D":
                    task = new Deadline(data[3], data[4]);
                    break;
                case "E":
                    task = new Event(data[3], data[4]);
                    break;
                default:
                    throw new DukeException("Failed to load tasks.");
                }

                markTaskDone(task, data[1]);

                switch (data[2]) {
                case "1":
                    task.setPriority(1);
                    break;
                case "2":
                    task.setPriority(2);
                    break;
                case "3":
                    task.setPriority(3);
                    break;
                case "4":
                    task.setPriority(4);
                    break;
                default:
                    throw new DukeException("Invalid priority value.");
                }

                tasks.add(task);
            }
            scanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File is not found");
        }
    }

    /**
     * Saves the current tasks in the list to the data file.
     *
     * @param tasks The TaskList to be saved to the destined filePath.
     * @throws DukeException If writing to file fails.
     */
    public void saveTasks(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                fw.write(task.serialize());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to save tasks");
        }
    }
}
