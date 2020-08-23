package duke.tasklist;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Class that simulates containing all the task.
 */

public class TaskList {
    private ArrayList<Task> listTasks;
    
    public TaskList() {
        listTasks = new ArrayList<>();
    }

    /**
     * Return the number of tasks in the list
     * 
     * @return Integer value of the size of the list
     */
    public int size() {
        return listTasks.size();
    }

    /**
     * Retrieves the task at that index 'n' of the list.
     * 
     * @param n Integer value.
     * @return A task at that index 'n'.
     */
    public Task get(int n) {
        return listTasks.get(n);
    }

    /**
     * Adds the tasks into the list
     * 
     * @param t Task to be added.
     */
    public void add(Task t) {
        listTasks.add(t);
    }

    /**
     * Removes the task as that index 'n' of the list.
     * 
     * @param n Integer value.
     */
    public void remove(int n) {
        listTasks.remove(n);
    }
}
