package duke.operation;

import duke.exception.DukeException;
import duke.storage.TaskStorage;
import duke.task.TaskList;

/**
 * The operation that exits from Duke.
 */
public class ExitOperation extends Operation {
    private final TaskStorage storage;
    private final TaskList taskList;

    /**
     * Constructor method.
     * @param storage the TaskStorage object that allows the taskList to be saved into a text file.
     * @param taskList the TaskList that is to be saved.
     */
    public ExitOperation(TaskStorage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Specifies that this is an Exit Operation.
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Saves the tasks in TaskList into a text file.
     * @return a goodbye message and an indication if the tasks cannot be saved.
     */
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