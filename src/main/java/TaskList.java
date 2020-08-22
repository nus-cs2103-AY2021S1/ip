import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a task list to hold all current tasks.
 */
public class TaskList {

    /**
     * Implements using a Java List.
     */
    private List<Task> taskList;

    /**
     * Constructor to initialize the list using ArrayList.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Deletes a task.
     *
     * @param index Index of task to be deleted.
     */
    public void deleteTask(int index) {
        taskList.remove(index);
    }

    /**
     * Gets the list object.
     *
     * @return List object.
     */
    public List<Task> getList() {
        return taskList;
    }

    /**
     * Checks if task list is empty.
     *
     * @return True if list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }

    /**
     * Returns size of task list.
     *
     * @return Size.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Retrieves task object at given index.
     *
     * @param index Index of task to be retrieved.
     * @return The corresponding task.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Formats the list size to be used for display purposes.
     *
     * @return Formatted string.
     */
    public String taskSizeString() {
        return "Now you have " + taskList.size() + " task(s) in the list.";
    }

    /**
     * Marks a given task as completed.
     *
     * @param taskNum Index of task to be marked.
     */
    public void markTaskDone(int taskNum) {
        Task task = taskList.get(taskNum);
        task.markDone();
    }
}
