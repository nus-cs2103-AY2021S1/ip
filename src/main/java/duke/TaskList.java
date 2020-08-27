package duke;

import duke.task.Task;

import java.util.List;

public class TaskList {

    protected List<Task> tasks;
    protected Storage storage = new Storage();

    public TaskList() {
        tasks = storage.getTasks();
    }

    // CREATE
    public void add(Task task) {
        storage.add(task);
        tasks.add(task);
    }

    // READ
    public List<Task> getTasks() {
        return tasks;
    }
    public int taskSize() {
        return tasks.size();
    }

    // UPDATE
    public Task markAsDone(int taskId) {
        Task task = tasks.get(taskId).markAsDone();
        tasks.set(taskId, task);
        storage.update();
        return task;
    }

    // DELETE
    public Task delete(int taskId) {
        Task task = tasks.get(taskId);
        tasks.remove(taskId);
        storage.update();
        return task;
    }

}
