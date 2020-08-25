package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.taskListHandler;

/**
 * Generic abstract command class with a execute method to be implemented.
 */
public abstract class Command {

    /**
     * Performs actions specific to each command.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    public abstract void execute(taskListHandler handler, Storage storage) throws DukeException;

}
