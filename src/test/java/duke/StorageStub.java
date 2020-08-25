package duke;

import duke.task.Task;

import java.io.IOException;
import java.util.List;

public class StorageStub extends Storage {
    public StorageStub() {
        super("");
    }

    @Override
    public void writeData(Task task) throws IOException {
    }

    @Override
    public void rewriteData(List<Task> tasks) throws IOException {
    }
}
