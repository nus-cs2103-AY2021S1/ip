package duke.command;

import duke.exception.DukeException;
import duke.main.Statement;

/**
 * Represents a bye command.
 */
public class ByeCommand implements Command {
    @Override
    public Response process() throws DukeException {
        return new Response(Statement.BYE.toString());
    }
}
