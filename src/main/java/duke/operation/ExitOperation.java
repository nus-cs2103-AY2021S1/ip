package duke.operation;

import duke.exception.DukeException;
import duke.storage.TaskStorage;
import duke.task.TaskList;

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
    public String execute() {
        String status = "";
        try {
            this.storage.saveToDisk(this.taskList);
        } catch (DukeException exception) {
           status += "The list of tasks cannot be saved.\n";
        }
        return status + "Goodbye. Hope to see you again soon.";
    }
}