package duke.command;

import duke.dukeexception.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a (bot) command that can be executed by the bot.
 */
public abstract class Command {

    /**
     * Executes the command as derived from the user's input.
     *
     * @param tasks The user's tasks.
     * @param storage Handles updating the hard disk accordingly.
     * @throws DukeException If a DukeException is thrown in executing the command.
     */
    public abstract void execute(TaskList tasks, Storage storage) throws DukeException;

    /**
     * Returns true when the command is an exit command and false otherwise.
     *
     * @return A boolean indicating if the command is an exit command.
     */
    public abstract boolean isExit();
}
