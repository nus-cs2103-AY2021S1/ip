package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;

/**
 * Throws DukeException when invalid command is given.
 * Inherits from generic command class.
 */
public class InvalidCommand extends Command {
    protected final String invalidInput;

    public InvalidCommand(String invalidInput) {
        this.invalidInput = invalidInput;
    }

    /**
     * Notifies user that command is not recognized and asks the user to input again.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage) {
        // Other commands
        try {
            throw new DukeException("\u2639 Oops, I'm sorry but I don't know what " + '"' + invalidInput + '"' + " means :-(");
        } catch (DukeException e) {
            e.printStackTrace(System.out);
            DukeException.tryAgain();
        }
    }
}
