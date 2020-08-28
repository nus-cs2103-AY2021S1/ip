package duke.task;

import duke.storage.Storage;

import java.util.ArrayList;

public class TaskArrayList implements TaskList {
    private final ArrayList<Task> TASK_LIST = new ArrayList<>();
    private final Storage STORE;

    public TaskArrayList(Storage store) {
        this.STORE = store;
    }

    public void add(Task t, boolean shouldUpdateStorage) {
        TASK_LIST.add(t);
        if (shouldUpdateStorage) {
            STORE.addLine(t.toSaveString());
        }
    }

    public Task get(int i) {
        return TASK_LIST.get(i);
    }

    public Task remove(int i) {
        STORE.removeLine(i);
        return TASK_LIST.remove(i);
    }

    public void update(int i) {
        STORE.updateLine(i, TASK_LIST.get(i).toSaveString());
    }

    public int size() {
        return TASK_LIST.size();
    }
}
