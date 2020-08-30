package duke.operation;

import duke.exception.DukeOperationException;
import duke.result.Result;
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
     * @param storage the <code>TaskStorage</code> that allows the
     *                <code>taskList</code> to be saved into a text file.
     * @param taskList the <code>TaskList</code> that is to be saved.
     */
    public ExitOperation(TaskStorage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
    }

    /**
     * Specifies that this is an <code>ExitOperation</code>.
     * @return <code>true</code>.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Saves the <code>Tasks</code> in <code>TaskList</code> into a text file.
     * If the file cannot be saved, Duke will not exit.
     * @return a goodbye message and an indication if the <code>Tasks</code> cannot be saved.
     */
    @Override
    public Result execute() {
        try {
            this.storage.saveToDisk(this.taskList);
            String message = "Goodbye. Hope to see you again soon.";
            return new Result(true, message, this.isExit());
        } catch (DukeOperationException exception) {
            String message = "The list of tasks cannot be saved.\n";
            return new Result(false, message + exception.getMessage(), false);
        }
    }
}
