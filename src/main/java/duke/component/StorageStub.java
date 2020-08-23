package duke.component;

import duke.task.Task;

public class StorageStub implements Storage {
    @Override
    public TaskList getList() {
        return null;
    }

    @Override
    public void addToList(Task task) {
    }

    @Override
    public void reWrite(TaskList list) {
    }
}
