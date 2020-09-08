import java.util.ArrayList;

/**
 * A class to handle the list of existing tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Overloaded constructor.
     * @param tasks list of existing tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of existing tasks.
     * @return the list of existing tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Set the tasks to the given list of tasks.
     * @param taskList new list of tasks.
     */
    public void setTaskList(TaskList taskList) {
        this.tasks = taskList.getTaskList();
    }
}
