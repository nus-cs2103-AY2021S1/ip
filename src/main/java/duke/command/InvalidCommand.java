package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;

/**
 * Throws DukeException when invalid command is given.
 * Inherits from generic command class.
 */
public class InvalidCommand extends Command {

    /**
     * Notifies user that command is not recognized and asks the user to input again.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage, String input) throws DukeException {
        throw new DukeException("\u2639 Whoops, I'm sorry but I don't know what "
            + '"' + input + '"' + " means :-(");
    }
}
