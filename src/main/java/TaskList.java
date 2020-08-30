import java.util.ArrayList;

/**
 * Represents a TaskList, which stores a list of Tasks.
 */
public class TaskList {
    ArrayList<Task> taskList;

    TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Return a new TaskList, with an added Task.
     *
     * @param task New task to be added to TaskList.
     * @return TaskList with added Task.
     */
    public TaskList add(Task task) {
        taskList.add(task);
        return new TaskList(taskList);
    }

    /**
     * Return a new TaskList, with a removed Task at the given index.
     *
     * @param index Index of task to be removed.
     * @return TaskList with removed Task.
     */
    public TaskList remove(int index) {
        taskList.remove(index);
        return new TaskList(taskList);
    }

    /**
     * Return the Task at a given index in TaskList.
     *
     * @param index Index of task to be retrieved.
     * @return Task of given index.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Return a new TaskList containing Tasks that match the search query.
     *
     * @param query Search query.
     * @return TaskList containing Tasks that match the search query.
     */
    public TaskList find(String query) {
        ArrayList<Task> newTaskList = new ArrayList<Task>();
        for (Task task : taskList) {
            if (task.getTask().contains(query)) {
                newTaskList.add(task);
            }
        }
        return new TaskList(newTaskList);
    }

    /**
     * Return the total number of items contained in the TaskList.
     *
     * @return The total number of items contained in the TaskList.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Set the index of TaskList to be given Task.
     *
     * @param index Index in TaskList of Task to be set.
     * @param task Task to be set.
     * @return TaskList with Task being set at specific index.
     */
    public TaskList set(int index, Task task) {
        taskList.set(index, task);
        return new TaskList(taskList);
    }
}
