package commands;

import exceptions.DukeException;
import exceptions.InvalidCommandException;
import service.DukeResponse;
import service.DukeService;


/**
 * This class represents Delete, which deletes task into the system.
 * Syntax: delete + task_index
 */
public class DeleteCommand extends Command {
    public static final String commandWord = "delete";
    private int position;

    /**
     * Constructor.
     * @param raw: raw command input by users
     */
    public DeleteCommand(String raw) {
        super((raw));
    }

    /**
     * Overriden method, to execute the command given the service
     * @param service: duke service
     * @return a duke response
     * @throws Exception if execution fails
     */
    @Override
    public DukeResponse execute(DukeService service) throws DukeException {
        if (!super.isParse) {
            throw new DukeException("Command has not been parsed");
        }
        return service.deleteTask(this.position);
    }

    /**
     * Overriden method, to parse the command.
     * @throws InvalidCommandException when syntax is wrong, and report to users.
     */
    @Override
    public void parse() throws InvalidCommandException {
        String[] tokens = super.raw.split(" ");

        if (tokens.length <= 1) {
            throw new InvalidCommandException("Not enough arguments");
        }

        if (tokens.length > 2) {
            throw new InvalidCommandException("I can just delete one task :(");
        }

        try {
            this.position = Integer.parseInt(tokens[1]);
            super.isParse = true;
        } catch (Exception e) {
            throw new InvalidCommandException("Please input an integer :(");
        }
    }
}
