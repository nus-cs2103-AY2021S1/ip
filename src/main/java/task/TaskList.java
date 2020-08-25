package task;

import java.util.List;
import storage.DataStorage;
import storage.DataStorageFactory;

public class TaskList {
    private DataStorage storage = DataStorageFactory.getInstance();
    private List<Task> tasks = storage.read();

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void add(Task task) {
        tasks.add(task);
        storage.update(tasks);
    }

    public void remove(int index) {
        tasks.remove(index);
        storage.update(tasks);
    }

    public boolean complete(int index) {
        Task task = tasks.get(index);
        if (task.isCompleted()) {
            return false;
        }
        task.setCompleted(true);
        storage.update(tasks);
        return true;
    }
}