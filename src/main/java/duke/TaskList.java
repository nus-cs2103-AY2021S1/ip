package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates a new instance of a TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new instance of a TaskList.
     *
     * @param tasks ArrayList of Task.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the arrayList of Task.
     *
     * @return An arrayList of Task.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
