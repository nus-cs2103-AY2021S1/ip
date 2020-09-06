package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents the Storage Object containing logic to writing and storing task list to memory.
 */
public class Storage {
    private String filePath;

    /**
     * Initializes Storage object to be used for saving and loading task list throughout Duke.
     *
     * @param filePath specified file for Storage object to read from and write to.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Handles logic where ./data or duke.txt does not exists by creating them.
     *
     * @param savedTasks File Object for loading and saving tasks.
     * @throws IOException if File object cannot create new file.
     */
    private void handleFileOrDirectoryDoesNotExist(File savedTasks) throws IOException {
        if (savedTasks.getParentFile().mkdirs()) {
            System.out.println("./data directory created");
        }
        if (savedTasks.createNewFile()) {
            System.out.println("duke.txt created in ./data");
        }
    }

    /**
     * Saves tasks list to memory.
     *
     * @param taskItems tasks list to be saved to memory.
     * @throws DukeException if task cannot be written to memory if file created in path.
     */
    public void saveTaskToMemory(List<Task> taskItems) throws DukeException {
        File savedTasks = new File(filePath);
        try {
            if (!savedTasks.exists()) {
                handleFileOrDirectoryDoesNotExist(savedTasks);
            }
            // Once file path exists, instantiate FileWriter Object.
            FileWriter fw = new FileWriter(filePath);
            String taskToWrite = "";
            for (Task task: taskItems) {
                if (task instanceof ToDo) {
                    taskToWrite += String.format("T,%d,%s\n", task.getIsDone() ? 1 : 0, task.getDescription());
                } else if (task instanceof Deadline) {
                    taskToWrite += String.format("D,%d,%s,%s\n", task.getIsDone() ? 1 : 0,
                                                task.getDescription(), ((Deadline) task).getByWhen());
                } else if (task instanceof Event) {
                    taskToWrite += String.format("E,%d,%s,%s\n", task.getIsDone() ? 1 : 0,
                                                task.getDescription(), ((Event) task).getAtWhen());
                } else {
                    throw new DukeException("Cannot save invalid task type");
                }
            }
            fw.write(taskToWrite);
            fw.close();
        } catch (IOException exception) {
            throw new DukeException(String.format("task cannot be saved to memory %s", exception.getMessage()));
        }
    }

    /**
     * Loads Tasks array from memory, else returns an empty array.
     *
     * @return List Object containing tasks loaded from memory.
     */
    public List<Task> loadTasksFromMemory() throws DukeException {
        List<Task> storedTasks = new ArrayList<>();
        try {
            File savedTasks = new File(filePath);
            // file or directory does not exists, make parent directory and file
            if (!savedTasks.exists()) {
                handleFileOrDirectoryDoesNotExist(savedTasks);
                return storedTasks;
            }
            // read and return from saved file
            Scanner sc = new Scanner(savedTasks);
            while (sc.hasNextLine()) {
                Task taskToAdd;
                String savedTask = sc.nextLine();
                // Array of task type, status, description and time
                String[] parsedTasks = savedTask.split(",");
                assert parsedTasks.length >= 3 : "task retrieved must have type, status, description";
                String taskType = parsedTasks[0];
                boolean isDone = parsedTasks[1].equals("1") ? true : false;
                String description = parsedTasks[2];
                String time;
                switch (taskType) {
                case "T":
                    taskToAdd = new ToDo(description, isDone);
                    break;
                case "D":
                    time = parsedTasks[3];
                    taskToAdd = new Deadline(description, isDone, LocalDate.parse(time));
                    break;
                case "E":
                    time = parsedTasks[3];
                    taskToAdd = new Event(description, isDone, LocalDate.parse(time));
                    break;
                default:
                    throw new DukeException("Task cannot be read from Duke.txt");
                }
                storedTasks.add(taskToAdd);
            }
            return storedTasks;
        } catch (IOException exception) {
            throw new DukeException(String.format("File cannot be loaded %s", exception.getMessage()));
        }
    }
}
