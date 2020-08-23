import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataSaver {
    public static final String FILE_PATH = "data/duke.txt";

    /**
     * Handles logic where ./data or duke.txt does not exists by creating them.
     */
    private static void handleFileOrDirectoryDoesNotExist(File savedTasks) throws IOException {
        if(savedTasks.getParentFile().mkdirs()) {
            System.out.println("./data directory created");
        }
        if (savedTasks.createNewFile()) {
            System.out.println("duke.txt created in ./data");
        }
    }

    public static void saveTaskToMemory(List<Task> taskList) throws DukeException {
        File savedTasks = new File(FILE_PATH);
        try {
            if (!savedTasks.exists()) {
                handleFileOrDirectoryDoesNotExist(savedTasks);
            }
            // Once file path exists, instantiate FileWriter Object.
            FileWriter fw = new FileWriter(FILE_PATH);
            String taskToWrite = "";
            for (Task task: taskList) {
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
     * Loads Tasks array from memory, else returns an empty array 
     * @return
     */
    public static ArrayList<Task> loadTasksFromMemory() throws DukeException {
        ArrayList<Task> tasksInMemory = new ArrayList<>();
        try {
            File savedTasks = new File(FILE_PATH);
            // file or directory does not exists, make parent directory and file
            if (!savedTasks.exists()) {
                handleFileOrDirectoryDoesNotExist(savedTasks);
                return tasksInMemory;
            }
            // read and return from saved file
            Scanner sc = new Scanner(savedTasks);
            while (sc.hasNextLine()) {
                Task taskToAdd;
                String savedTask = sc.nextLine();
                // Array of task type, status, description and time
                String[] parsedTask = savedTask.split(",");
                String taskType = parsedTask[0];
                boolean isDone = parsedTask[1].equals("1") ? true : false;
                String description = parsedTask[2];
                String time;
                switch (taskType) {
                case "T":
                    taskToAdd = new ToDo(description, isDone);
                    break;
                case "D":
                    time = parsedTask[3];
                    taskToAdd = new Deadline(description, isDone, time);
                    break;
                case "E":
                    time = parsedTask[3];
                    taskToAdd = new Event(description, isDone, time);
                    break;
                default:
                   throw new DukeException("Task cannot be read from Duke.txt");
                }
                tasksInMemory.add(taskToAdd);
            }
            return tasksInMemory;
        } catch (IOException exception) {
            throw new DukeException(String.format("File cannot be loaded %s", exception.getMessage()));
        }
    }
}
