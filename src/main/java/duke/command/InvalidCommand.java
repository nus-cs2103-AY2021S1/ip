package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Command for when user inputs an invalid command.
 */
public class InvalidCommand extends Command {
    public InvalidCommand() {}

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("Sorry that is not a valid command!");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
