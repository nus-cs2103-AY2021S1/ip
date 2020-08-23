package duke.util;

import duke.task.Task;

import java.util.List;

public class TaskList {

    private List<Task> store;

    public TaskList(List<Task> lst) {
        this.store = lst;
    }

    public List<Task> getList() {
        return store;
    }

    public int numberOfTasks() {
        return store.size();
    }

    public Task doneTask(int num) {
        store.get(num - 1).setDone();
        return store.get(num - 1);
    }

    public Task deleteTask(int num) {
        Task task = store.get(num - 1);
        store.remove(task);
        return task;
    }

    public void addTask(Task task) {
        store.add(task);
    }

}
