package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

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
        boolean status = (string.charAt(4) == '\u2713');
        if (string.charAt(1) == 'T') {
            return new ToDo(string.substring(7), status);
        } else if (string.charAt(1) == 'D') {
            int endIndex = string.indexOf("(by:") - 1;
            return new Deadline(string.substring(7, endIndex), string.substring(endIndex + 6, string.length() - 1), status);
        } else {
            int endIndex = string.indexOf("(at:") - 1;
            return new Event(string.substring(7, endIndex), string.substring(endIndex + 6, string.length() - 1), status);
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
        for (Task task : tasksToSave.getListOfTasks())
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }

    /**
     * Locate the storage file (creating a new file if it does not exist) and adds
     * all the existing tasks in the file to an ArrayList
     *
     * @return ArrayList of all existing tasks
     * @throws IOException if filePath does not exist
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> task = new ArrayList<>();
        File f = new File(this.filePath);
        if (!f.exists()) {
            f.createNewFile();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String currentTask = s.nextLine();
            task.add(genTaskFromString(currentTask));
        }
        return task;
    }
}
