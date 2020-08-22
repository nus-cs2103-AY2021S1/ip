import java.util.ArrayList;
import java.util.List;

/**
 * Handles manipulation of the list of Tasks.
 */

public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList.
     * @param tasks Initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets list of tasks.
     * @return List of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Gets size of the list of tasks.
     * @return Size of list of tasks.
     */
    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Adds an input task to the list of tasks.
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(">> Added the task:\n>> " + task + "\n>> You now have " + tasks.size() + " tasks to do!");
    }

    /**
     * Deletes a task to the list of tasks.
     * @param idx Index of task to be deleted.
     */
    public void deleteTask(int idx) {
        Task task = tasks.get(idx);
        tasks.remove(idx);
        System.out.println(">> I've eradicated the task:\n>> " + task + "\n>> You now have " + tasks.size() + " tasks to do!");
    }


    /**
     * Marks a task in the list of tasks as completed.
     * @param idx Index of task to be completed.
     */
    public void completeTask(int idx) {
        tasks.get(idx).complete();
        System.out.println(">> Yay! The following task is marked as done:\n>> " + tasks.get(idx));
    }
}
