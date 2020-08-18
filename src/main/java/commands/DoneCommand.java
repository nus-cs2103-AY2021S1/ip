package commands;

import service.DukeResponse;
import service.DukeService;

public class DoneCommand extends Command {
    public static final String commandWord = "done";
    private int position;

    public DoneCommand(String raw) {
        super(raw);
    }

    @Override
    public DukeResponse execute(DukeService service) throws Exception {
        if (!super.isParse) {
             throw new Exception("Command has not been parsed");
        }
        return service.markAsDone(position);
    }

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
