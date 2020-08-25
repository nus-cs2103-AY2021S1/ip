package commands;

/**
 * Base class of a Command in Duke.
 */

import data.exception.DukeException;

public abstract class Command {

    /**
     * Executes the given command.
     * @throws DukeException when a certain constraint has not been met.
     */
    public abstract void execute() throws DukeException;
}
