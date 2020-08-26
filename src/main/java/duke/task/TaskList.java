package duke.task;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list in which all the tasks are stored while Duke is running.
 */
public class TaskList {
    private List<Task> tasks;
    
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * A function to add a task to the list.
     * @param task The task that should be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * A function to get a specific task from the list.
     * @param index the number representing the task in the list.
     * @return a Task from the list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * A function to get the number of tasks in the list.
     * @return an integer that represents the number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * A function to remove a task from the list.
     * @param index the number representing the task that should be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }
}

