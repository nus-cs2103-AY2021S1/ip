package duke.util;

import duke.task.Task;

import java.util.List;

/**
 * Represents the list of tasks.
 */
public class TaskList {

    private List<Task> store;

    public TaskList(List<Task> lst) {
        this.store = lst;
    }

    public List<Task> getList() {
        return store;
    }

    /**
     * Returns number of tasks in the list of tasks.
     * @return Number of tasks.
     */
    public int getNumberOfTasks() {
        return store.size();
    }

    /**
     * Marks a task in the list of tasks as done.
     * @param num The task number of the Task.
     * @return Task which was marked as done.
     */
    public Task completeTask(int num) {
        store.get(num - 1).setDone();
        return store.get(num - 1);
    }

    /**
     * Deletes a task from the list of tasks.
     * @param num The task number of the Task.
     * @return Task which was deleted.
     */
    public Task deleteTask(int num) {
        Task task = store.get(num - 1);
        store.remove(task);
        return task;
    }

    /**
     * Adds a Task to the list of tasks.
     * @param task The Task to be added.
     */
    public void addTask(Task task) {
        store.add(task);
    }

}
