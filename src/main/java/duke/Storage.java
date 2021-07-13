package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents the part of Duke that deals with loading and saving tasks.
 */
public class Storage {
    /** The file where tasks are loaded from and saved to */
    private final File file;

    /**
     * Creates a new Storage where tasks can be loaded from and saved to.
     * If the directory or file does not exist, new ones will be created.
     *
     * @param filePath the filepath where tasks are loaded from and saved to.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Uh-oh! An I/O error occurred.");
        }
    }

    /**
     * Loads tasks that were previously saved on an existing file.
     *
     * @return a list of all previous tasks from an existing file.
     * @throws DukeException if there are issues while loading the previous tasks.
     */
    public List<Task> loadTasks() throws DukeException {
        try {
            Scanner sc = new Scanner(file);
            List<Task> tasks = new ArrayList<>();

            while (sc.hasNext()) {
                String taskDetails = sc.nextLine();
                Task task = extractTask(taskDetails);
                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("Uh-oh! The file could not be found!");
        }
    }

    /**
     * Saves current tasks onto a file.
     *
     * @param tasks the list of all the current tasks to be saved.
     */
    public void save(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("./data/tasks.txt");

            for (Task t : tasks) {
                fw.write(t.saveAs() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Uh-oh! An I/O error occurred.");
        }
    }

    /**
     * Extracts the specific task from the 'saved' format.
     *
     * @param taskDetails the task in 'saved' format.
     * @return the extracted task.
     */
    public Task extractTask(String taskDetails) {
        if (taskDetails.startsWith("T")) {
            return ToDo.load(taskDetails);
        } else if (taskDetails.startsWith("D")) {
            return Deadline.load(taskDetails);
        } else { // taskDetails starts with "E"
            return Event.load(taskDetails);
        }
    }
}
