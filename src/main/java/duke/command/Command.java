package duke.command;

import duke.exception.DukeException;

public interface Command {

    /**
     * Executes a command.
     *
     * @return a response.
     */
    Response process() throws DukeException;
}
