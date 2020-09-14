package duke.command;

import duke.DukeException;

/**
 * Command in the event that the input is invalid.
 */
public class InvalidCommand implements Command {

    @Override
    public String executeWithResponse() throws DukeException {
        throw new DukeException("I'm sorry, but I don't know what that means :-(");
    }

    @Override
    public boolean continueDuke() {
        return true;
    }

}
