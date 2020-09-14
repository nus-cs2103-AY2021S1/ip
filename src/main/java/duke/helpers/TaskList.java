package duke.helpers;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;


/**
 * contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private List<Task> allTasks; //Contains all the current tasks
    /**
     * Assigns allTasks a value
     *
     * @param tasks assings the mem var a value of allTasks
     */
    public TaskList(List<Task> tasks) {
        this.allTasks = new ArrayList<>(tasks);
    }

    /**
     * another constructor, where the allTasks variable is just empty
     */
    public TaskList() {
        allTasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return the list of tasks.
     */
    public List<Task> getAllTasks() {
        return allTasks;
    }
}
