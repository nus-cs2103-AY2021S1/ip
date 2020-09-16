package duke.command;

import duke.exception.DukeException;

/**
 * Represents a help command.
 */
public class HelpCommand implements Command {
    @Override
    public Response process() throws DukeException {
        return new Response(CommandString.showHelp().toString());
    }
}
