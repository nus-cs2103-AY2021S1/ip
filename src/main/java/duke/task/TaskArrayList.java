package duke.task;

import java.util.ArrayList;

import duke.storage.Storage;

// An implementation of the TaskList interface using an ArrayList
public class TaskArrayList implements TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final Storage store;

    public TaskArrayList(Storage store) {
        this.store = store;
    }

    @Override
    public void add(Task t, boolean shouldUpdateStorage) {
        tasks.add(t);
        if (shouldUpdateStorage) {
            store.addLine(t.toSaveString());
        }
    }

    @Override
    public Task get(int i) {
        return tasks.get(i);
    }

    @Override
    public Task remove(int i) {
        store.removeLine(i);
        return tasks.remove(i);
    }

    @Override
    public void update(int i) {
        store.updateLine(i, tasks.get(i).toSaveString());
    }

    @Override
    public int size() {
        return tasks.size();
    }
}
