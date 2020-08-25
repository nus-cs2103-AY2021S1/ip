package duke.task;

import java.util.ArrayList;
import java.util.List;
import duke.storage.FileWritingException;
import duke.storage.TaskListStorage;

public class TaskList {
    private List<Task> list;
    private TaskListStorage storage;
    private FileWritingExceptionHandler handler;

    public TaskList() {
        list = new ArrayList<>();
        storage = null;
        handler = (e) -> {};
    }

    // make Duke implement error handler to avoid having to propagate error to Command classes
    public void connectStorage(TaskListStorage storage, FileWritingExceptionHandler handler) {
        this.storage = storage;
        this.handler = handler;
    }

    public void add(Task task) {
        list.add(task);
        save();
    }

    public Task delete(int index) {
        Task removed = list.remove(index);
        save();
        return removed;
    }

    public Task get(int index) {
        return list.get(index);
    }

    public int size() {
        return list.size();
    }

    public void markAsDone(int index) {
        get(index).markAsDone();
        save();
    }

    private void save() {
        if (storage != null) {
            try {
                storage.save(this);
            } catch (FileWritingException e) {
                handler.handle(e);
            }
        }
    }
}
