import java.util.ArrayList;

/**
 * Container class for Task
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Add a Task to the end of the list
     * @param newTask - Task to add
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * Insert a Task to a position of the list
     * @param newTask - Task to add
     * @param index - position of newTask after inserting
     */
    public void addTask(Task newTask, int index) {
        if (index == -1) {
            index = size();
        }
        tasks.add(index, newTask);
    }

    /**
     * Remove a Task from the list
     * @param pos - position to remove (0-based)
     */
    public void removeTask(int pos) {
        tasks.remove(pos);
    }

    /**
     * @return the container's content
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * @return the container's size
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieve a Task in the specified position
     * @param pos - position to retrieve (0-based)
     * @return the Task at position pos
     */
    public Task get(int pos) {
        return this.tasks.get(pos);
    }
}
