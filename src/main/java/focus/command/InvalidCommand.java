package focus.command;

import focus.exception.FocusException;
import focus.storage.Storage;
import focus.task.TaskList;

/** Represents the InvalidCommand to deal with invalid inputs by user. */
public class InvalidCommand extends Command {
    /**
     * Returns false since InvalidCommand is not an ExitCommand.
     *
     * @return False.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes InvalidCommand to notify user that the inputs are not recognised.
     *
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @return String representation of invalid command.
     * @throws FocusException Always since input is invalid.
     */
    public String execute(String input, TaskList taskList, Storage storage) throws FocusException {
        throw new FocusException("\tOops! I'm not sure what you meant!\n"
                + "\tPlease try again!");
    }
}
