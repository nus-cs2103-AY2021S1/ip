package duke;

import duke.task.Task;

import java.util.List;

/**
 * The TaskList handles the modification of list of task.
 * It supports the complete CRUD operations.
 */
public class TaskList {

    protected List<Task> tasks;
    protected Storage storage = new Storage();

    public TaskList() {
        tasks = storage.getTasks();
    }

    /**
     * Adds the given task to the list and update the Duke.txt file.
     *
     * @param task
     */
    public void add(Task task) {
        storage.add(task);
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }
    public int taskSize() {
        return tasks.size();
    }

    /**
     * Marks the task with the given taskId as done and update the Duke.txt file.
     *
     * @param taskId
     * @return task that is marked done.
     */
    public Task markAsDone(int taskId) {
        Task task = tasks.get(taskId).markAsDone();
        tasks.set(taskId, task);
        storage.update();
        return task;
    }

    /**
     * Deletes the task with the given taskId and update the Duke.txt file.
     *
     * @param taskId
     * @return task that is deleted.
     */
    public Task delete(int taskId) {
        Task task = tasks.get(taskId);
        tasks.remove(taskId);
        storage.update();
        return task;
    }

}
