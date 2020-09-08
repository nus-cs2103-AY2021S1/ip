package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 *  Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for creating storage object
     *
     * @param filePath relative directory of the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Generates a Task object given string of form: [Type of Task][symbol] description (by: )/(at: )
     *
     * @param string task in string form
     * @return corresponding Task object
     */
    public static Task genTaskFromString(String string) {
        assert string.charAt(1) == 'T' || string.charAt(1) == 'D' || string.charAt(1) == 'E';
        boolean status = (string.charAt(4) == '\u2713');
        char taskType = string.charAt(1);

        if (taskType == 'T') {
            return new ToDo(string.substring(7), status);
        } else if (taskType == 'D') {
            int endIndex = string.indexOf("(by:") - 1;
            String taskString = string.substring(7, endIndex);
            String dateTimeString = string.substring(endIndex + 6, string.length() - 1);
            return new Deadline(taskString, dateTimeString, status);
        } else {
            int endIndex = string.indexOf("(at:") - 1;
            String taskString = string.substring(7, endIndex);
            String atString = string.substring(endIndex + 6, string.length() - 1);
            return new Event(taskString, atString, status);
        }
    }

    /**
     * Clears the storage file
     *
     * @throws IOException if filePath does not exist
     */
    public void clear() throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        fw.write("");
        fw.close();
    }

    /**
     * Saves the given task list to the storage filePath
     *
     * @param tasksToSave a TaskList of all existing tasks
     * @throws IOException if filePath does not exist
     */
    public void save(TaskList tasksToSave) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        for (Task task : tasksToSave.getListOfTasks()) {
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Adds a task to the storage filePath
     *
     * @param task task to be added
     * @throws IOException if filePath does not exist
     */
    public void append(Task task) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }

    /**
     * Carries out clear() and save() sequentially
     *
     * @param tasksToRefresh a TaskList of all existing tasks
     * @throws IOException if filePath does not exist
     */
    public void refresh(TaskList tasksToRefresh) throws IOException {
        this.clear();
        this.save(tasksToRefresh);
    }

    /**
     * Locate the storage file (creating a new file if it does not exist) and adds
     * all the existing tasks in the file to an ArrayList
     *
     * @return ArrayList of all existing tasks
     * @throws IOException if filePath does not exist
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(this.filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String currentTask = s.nextLine();
            tasks.add(genTaskFromString(currentTask));
        }
        return tasks;
    }
}
