package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * A command based on the user input that will be sent to Duke.
 */
public abstract class Command {

    public abstract void execute(TaskList task, Ui ui, Storage storage) throws DukeException;

    /**
     * Whether or not Duke program should terminate.
     * @return true only when the command is bye.
     */
    public boolean isExit() {
        return false;
    }
}
