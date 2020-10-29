package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;

/**
 * Generic abstract command class with a execute method to be implemented.
 */
public abstract class Command {

    /**
     * Performs actions specific to each command.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     * @param input User input.
     *
     * @throws DukeException If error encountered while executing command.
     */
    public abstract void execute(TaskListHandler handler, Storage storage, String input) throws DukeException;

}
