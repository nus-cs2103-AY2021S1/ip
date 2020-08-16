package task;

import java.util.ArrayList;

public class TaskStorage {
    private final ArrayList<Task> storage;

    public TaskStorage() {
        this.storage = new ArrayList<>();
    }

    public int getCurrCapacity() {
        return this.storage.size();
    }

    public Task addTask(Task task) {
        this.storage.add(task);
        return task;
    }

    public Task completeTask(int index) {
        Task task = this.storage.get(index - 1);
        task.completeTask();
        return task;
    }

    public Task removeTask(int index) {
        return this.storage.remove(index - 1);
    }

    public void printTaskStorage() {
        for (int i = 0; i < this.storage.size(); i++) {
            String output = String.format("%d. %s", i + 1, this.storage.get(i));
            System.out.println(output);
        }
    }
}
