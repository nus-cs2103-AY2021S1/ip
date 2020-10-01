package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class StorageDouble extends Storage {
    StorageDouble(String filePath) {
        super(filePath);
    }

    @Override
    public void initialize() {
        // nothing
    }

    @Override
    public TaskList readTasks() {
        return new TaskListDouble();
    }

    @Override
    public void writeToFile(TaskList taskList) {
        // nothing
    }

    @Override
    public void appendToFile(Task task) {
        // nothing
    }
}
