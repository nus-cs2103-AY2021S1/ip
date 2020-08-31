package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command from the user.
 */
public abstract class Command {

    /**
     * Creates a new Command.
     */
    public Command() {
    }

    /**
     * Executes the given command with the specified tasks, ui and storage and returns a response.
     * @param tasks The tasks the command is executed with.
     * @param storage The storage the command is executed with.
     * @return A command response that represents the result of completing a command.
     * @throws DukeException If there was a problem with executing the command.
     */
    public abstract CommandResponse execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Indicates if this command is an exit command.
     * @return False by default, unless this is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
