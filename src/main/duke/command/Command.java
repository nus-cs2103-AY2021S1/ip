package duke.command;

import duke.exception.DukeExecutionException;
import duke.storage.Storage;

/**
 * Represents an action input by the user to be executed by the program.
 */
public abstract class Command {

    /**
     * Returns true only if the program should terminate upon receiving this Command.
     * Default: false
     *
     * @return true if the program should exit, false otherwise.
     */
    public boolean shouldExit() {
        return false;
    }

    /**
     * Attempts to carry out the operation as written in the respective subclass's implementation.
     *
     * @param storage The Storage object to perform the operations on.
     * @throws DukeExecutionException if the program was unable to execute the action.
     */
    public abstract void execute(Storage storage) throws DukeExecutionException;

}
