package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Stores the location of the saved TaskList.
 */

public class Storage {
    private String filepath;

    /**
     * Creates a new storage with the specified directory.
     *
     * @param filepath directory to save to and load from
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads the previously saved tasks into an arraylist.
     * If there is no saved directory or file, create a new folder and arraylist.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            reader.lines().forEach(line -> loadTask(line, tasks));
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("Starting a new task list");
            new File("./data").mkdir();
            return tasks;
        }
    }

    /**
     * Saves the current list of tasks into the directory.
     *
     * @param tasks Current list of tasks.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filepath);
            for (Task t : tasks.getTasks()) {
                writer.write(t.store() + "\n");
            }
            writer.close();
        } catch (Exception e) {
            System.out.println("error in saving");
        }
    }

    private void loadTask(String str, ArrayList<Task> tasks) {
        if (str.startsWith("T")) {
            tasks.add(ToDo.load(str));
        } else if (str.startsWith("E")) {
            tasks.add(Event.load(str));
        } else if (str.startsWith("D")) {
            tasks.add(Deadline.load(str));
        }
    }
}
