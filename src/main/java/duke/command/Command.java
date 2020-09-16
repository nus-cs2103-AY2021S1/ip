package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * Represents input commands from user to be executed.
 */
public abstract class Command {

    /**
     * Executes the input command.
     *
     * @param taskList The existing task list.
     * @param ui The UI instance which handles Duke's user interface.
     * @param storage The existing storage for Duke.
     * @throws DukeException When an error related to Duke occurs during command execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns if the program should continue running at the current point in time.
     * If not, the program should be exited.
     *
     * @return If the program should continue running.
     */
    public abstract boolean isInProgram();
}
