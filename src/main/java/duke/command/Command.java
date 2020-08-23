package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents all the commands to allow different command classes to inherit.
 */
public abstract class Command {
    /**
     * Returns true or false depending on the type of command.
     * @return True or false.
     */
    public abstract boolean isExit();

    /**
     * Executes the command.
     * @param input User's input.
     * @param taskList Task list created for user.
     * @param storage Storage created for user.
     * @throws DukeException If input does not meet criteria.
     */
    public abstract void execute(String input, TaskList taskList, Storage storage)
            throws DukeException;
}
