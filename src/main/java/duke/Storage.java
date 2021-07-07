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
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /** File where tasks are saved and loaded from. */
    private final File file;

    /**
     * Creates a new Storage file where tasks can be loaded from and saved to.
     *
     * @param filepath represents the path of the file.
     */
    public Storage(String filepath) {
        this.file = new File(filepath);
        file.getParentFile().mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file.
     *
     * @return a list of all the tasks from the file.
     * @throws DukeException when there are problems with loading from the file.
     */
    public List<Task> load() throws DukeException {
        try {
            Scanner sc = new Scanner(file);
            List<Task> taskList = new ArrayList<>();

            while (sc.hasNextLine()) {
                String loadTask = sc.nextLine();
                Task task;
                if (loadTask.startsWith("T")) {
                    task = ToDo.load(loadTask);
                } else if (loadTask.startsWith("D")) {
                    task = Deadline.load(loadTask);
                } else if (loadTask.startsWith("E")) {
                    task = Event.load(loadTask);
                } else {
                    break;
                }
                taskList.add(task);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("Sorry!!! File not found.");
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry!!! Previous tasks not saved properly.");
        }
    }

    /**
     * Saves tasks into the file.
     *
     * @param tasks takes in a list of tasks to be saved into the file.
     * @throws DukeException when there are problems saving into the file.
     */
    public void save(List<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                int isFinished = task.getIsDone() ? 1 : 0;
                fw.write(task.save(isFinished) + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            throw new DukeException("Sorry!!! Encountered difficulties when saving tasks.");
        }
    }
}
