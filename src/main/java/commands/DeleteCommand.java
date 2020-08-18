package commands;

import exceptions.DukeException;
import exceptions.InvalidCommandException;
import service.DukeResponse;
import service.DukeService;

public class DeleteCommand extends Command {
    public static final String commandWord = "delete";
    private int position;

    public DeleteCommand(String raw) {
        super((raw));
    }

    @Override
    public DukeResponse execute(DukeService service) throws DukeException {
        if (!super.isParse) {
            throw new DukeException("Command has not been parsed");
        }
        return service.deleteTask(this.position);
    }

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
