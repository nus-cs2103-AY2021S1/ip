package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the part of Duke that deals with loading tasks from a file and saving tasks in the same file.
 */
public class Storage {
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
            e.printStackTrace();
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
                Task task;
                if (taskDetails.startsWith("T")) {
                    task = ToDo.load(taskDetails);
                } else if (taskDetails.startsWith("D")) {
                    task = Deadline.load(taskDetails);
                } else if (taskDetails.startsWith("E")) {
                    task = Event.load(taskDetails);
                } else {
                    throw new IllegalArgumentException();
                }
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
            e.printStackTrace();
        }
    }
}