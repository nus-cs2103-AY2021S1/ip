package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage class handles data, specifically tasks that needs to be saved after the applications is finished and
 * when the application is first launched to load the saved data.
 */
public class Storage {

    public static final String SAVED_FILE = "data/storage.txt";
    public static final String SAVED_FOLDER = "data";
    private Task task;

    /**
     * Constructor for storage class.
     */
    public Storage() {}

    /**
     * Constructor for storage class that takes in a task
     * @param task
     */
    public Storage(Task task) {
        this.task = task;
    }

    /**
     * Reads the file and converts the file to a list.
     * @return a list of tasks having read the file from the save storage
     * @throws IOException
     */
    public static List<Task> readFile() throws IOException, DukeDateException {
        List<Task> todoList = new ArrayList<>();
        Path path = Paths.get(SAVED_FILE);
        List<String> list = new ArrayList<>(Files.readAllLines(path));
        for (String task : list) {
            Task current = null;
            String[] split = task.split(" \\| ");
            if (split[0].equals("T")) {
                current = new Todo(split[2]);
            } else if (split[0].equals("E")) {
                current = new Event(split[2], split[3]);
            } else if (split[0].equals("D")) {
                current = new Deadline(split[2], split[3]);
            }
            if (split[1].equals("1")) {
                current.markAsDone();
            }
            todoList.add(current);
        }
        return todoList;
    }

    /**
     * Takes a task and adds the task to the file to be saved.
     * @param task the task that is going to be saved
     * @throws IOException
     */
    public static void addToFile(String task) throws IOException {
        File file = new File(SAVED_FILE);
        File fold = new File(SAVED_FOLDER);
        if (!fold.isDirectory()) {
            fold.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, true);
        fw.write(task + "\n");
        fw.close();
    }

    /**
     * Converts the task in the saved file and converts a task from incomplete to complete.
     * @param number the specific task number
     * @throws IOException
     */
    public static void editFile(int number) throws IOException {
        Path path = Paths.get(SAVED_FILE);
        List<String> list = new ArrayList<>(Files.readAllLines(path));
        String doneTask = list.get(number).replace("0", "1");
        list.set(number, doneTask);
        Files.write(path, list);
    }

    /**
     * Deletes a task from the list.
     * @param number the specific task to be deleted
     * @throws IOException
     */
    public static void deleteTask(int number) throws IOException {
        Path path = Paths.get(SAVED_FILE);
        List<String> list = new ArrayList<>(Files.readAllLines(path));
        list.remove(number - 1);
        Files.write(path, list);
    }

}
