package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        String errorMessage = "Sorry! I don't know what that means...\n";
        throw new DukeException(errorMessage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}