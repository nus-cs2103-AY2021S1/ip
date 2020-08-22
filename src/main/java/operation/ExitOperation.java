package operation;

import exception.DukeException;
import storage.TaskStorage;
import task.TaskList;

public class ExitOperation extends Operation {
    private final TaskStorage storage;
    private final TaskList taskList;

    public ExitOperation(TaskStorage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute() {
        try {
            this.storage.saveToDisk(this.taskList);
        } catch (DukeException exception) {
           System.out.println("The list of tasks cannot be saved.");
        }
        System.out.println("Goodbye. Hope to see you again soon.");
    }
}