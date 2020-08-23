package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the InvalidCommand to deal with invalid inputs by user.
 */
public class InvalidCommand extends Command {
    /**
     * Returns false since InvalidCommand is not an ExitCommand.
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes InvalidCommand to notify user that the inputs are not recognised.
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @throws DukeException Always since input is invalid.
     */
    public void execute(String input, TaskList taskList, Storage storage) throws DukeException {
        throw new DukeException("\tOops! I'm not sure what you meant!\n"
                + "\tPlease try again!");
    }
}
