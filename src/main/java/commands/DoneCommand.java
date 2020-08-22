package commands;

import exceptions.InvalidCommandException;
import service.DukeResponse;
import service.DukeService;

/**
 * This class represents DoneCommand which marks tasks as done
 * Syntax: done + task_id.
 */
public class DoneCommand extends Command {
    public static final String commandWord = "done";
    private int position;

    /**
     * Constructor.
     * @param raw: raw command input by users
     */
    public DoneCommand(String raw) {
        super(raw);
    }

    /**
     * Overriden method, to execute the command given the service
     * @param service: duke service
     * @return a duke response
     * @throws Exception if execution fails
     */
    @Override
    public DukeResponse execute(DukeService service) throws Exception {
        if (!super.isParse) {
             throw new Exception("Command has not been parsed");
        }
        return service.markAsDone(position);
    }

    /**
     * Overriden method, to parse the command.
     * @throws InvalidCommandException when syntax is wrong, and report to users.
     */
    @Override
    public void parse() throws Exception {
        String[] tokens = raw.split(" ");
        if (tokens.length > 2) {
            throw new Exception("Done command invalid: number of tokens should less than 3");
        }

        try {
            this.position = Integer.parseInt(tokens[1]);
            super.isParse = true;
        } catch (NumberFormatException e) {
            throw new Exception("Done command invalid: input should be an integer");
        }
    }
}
