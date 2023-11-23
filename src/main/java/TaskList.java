import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a tasklist to store all the tasks and cached tasks.
 */
public class TaskList {
    private List<Task> tasks;
    private Stack<CachedTask> cachedTasks;

    /**
     * Initialise the tasklist with an arraylist. Initialise new cache stacks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        cachedTasks = new Stack<>();
    }

    /**
     * Adds a task to the tasklist. Pushes added task to cached tasks.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        cachedTasks.push(new CachedTask(task, "add", getTasksSize() - 1));
    }

    /**
     * Returns the size of the tasklist.
     * @return Size of tasklist.
     */
    public int getTasksSize() {
        return tasks.size();
    }

    /**
     * Returns the size of the cached tasks.
     * @return Size of cached tasks.
     */
    public int getCachedTasksSize() {
        return cachedTasks.size();
    }

    /**
     * Gets a specific task from the list based on the index.
     * @param taskIndex index of task in tasklist.
     * @return Specific task
     */
    public Task get(int taskIndex) {
        return tasks.get(taskIndex);
    }

    /**
     * Returns the last cached task by popping it from the stack.
     * @return Most recently cached task.
     */
    public CachedTask getLastCachedTask() {
        return cachedTasks.pop();
    }

    /**
     * Returns the tasks stored in the tasklist.
     * @return ArrayList inside tasklist.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Removes the task from the list based on the index. Pushes deleted task to cached tasks.
     * @param taskIndex index of task in tasklist.
     * @param shouldAllowUndo true if undo command is used to delete
     */
    public void delete(int taskIndex, boolean shouldAllowUndo) {
        Task removedTask = tasks.remove(taskIndex);
        if (shouldAllowUndo) {
            cachedTasks.push(new CachedTask(removedTask, "delete", taskIndex));
        }
    }

    /**
     * Indicate task as completed in the tasklist. Pushes done task to cached tasks.
     * @param taskIndex index of task in tasklist.
     */
    public void markTaskCompleted(int taskIndex) {
        Task taskToMark = tasks.get(taskIndex);
        taskToMark.markTaskAsCompleted();
        cachedTasks.push(new CachedTask(taskToMark, "done", taskIndex));
    }

    /**
     * Indicate task as uncompleted in the tasklist as part of undo command.
     * @param taskIndex
     */
    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).markTaskAsUncompleted();
    }

    /**
     * Retrieves the deleted task from cached tasks upon undo command call.
     * @param index index of deleted task.
     * @param task deleted task to be recovered.
     */
    public void recoverTask(int index, Task task) {
        tasks.add(index, task);
    }
}
