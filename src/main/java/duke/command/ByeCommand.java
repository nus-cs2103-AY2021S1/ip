package duke.command;

import duke.exception.DukeException;
import duke.main.Statement;

public class ByeCommand implements Command {
    protected static final String FUNCTION = "[" + CommandString.BYE + "]";

    @Override
    public Response process() throws DukeException {
        return new Response(Statement.BYE.toString());
    }
}
