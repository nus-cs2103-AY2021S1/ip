package duke;

import java.io.IOException;
import java.util.List;

import duke.task.Task;


public class StorageStub extends Storage {
    public StorageStub() {
        super("");
    }

    @Override
    public void initialiseTasks(List<Task> tasks) {
    }

    @Override
    public void writeData(Task task) throws IOException {
    }

    @Override
    public void rewriteData(List<Task> tasks) throws IOException {
    }
}
