package duke.command;

import duke.exception.DukeException;

/**
 * Represents a command interface.
 */
public interface Command {

    /**
     * Executes a command and makes respective response to the user.
     *
     * @return a response to the user.
     */
    Response process() throws DukeException;
}
