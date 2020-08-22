package commands;

import exceptions.InvalidCommandException;
import service.DukeResponse;
import service.DukeService;

/**
 * This class represents ByeCommand
 * Syntax: bye
 */
public class ByeCommand extends Command {
    public static final String commandWord = "bye";
    private static final String BYE_STATEMENT = "Bye. Hope to see you again soon!\n";

    /**
     * Constructor.
     * @param raw: raw command input by users
     */
    public ByeCommand(String raw) {
        super(raw);
    }

    @Override
    public DukeResponse execute(DukeService service) {
        return new DukeResponse(BYE_STATEMENT);
    }

    /**
     * Overriden method, to parse the command.
     * @throws InvalidCommandException when syntax is wrong, and report to users.
     */
    @Override
    public void parse() throws Exception {
    }
}
