package duke.command;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        String errorMessage = "Sorry! I don't know what that means...\n";
        throw new DukeException(errorMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
