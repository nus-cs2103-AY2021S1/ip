package commands;

import service.DukeResponse;
import service.DukeService;

public class AddCommand extends Command {
    public static final String commandWord = "add";

    private String description;

    public AddCommand(String raw) {
        super(raw);
    }

    @Override
    public DukeResponse execute(DukeService service) throws Exception {
        if (!super.isParse) {
            throw new Exception("Command has not been parsed");
        }
        return service.addTask(this.description);
    }

    @Override
    public void parse() throws Exception {
        String[] tokens = super.raw.split(" ");

        if (tokens.length <= 1) {
            throw new Exception("Not enough arguments");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tokens.length; i++) {
            sb.append(tokens[i]);
            if (i + 1 < tokens.length) {
                sb.append(" ");
            }
        }
        this.description = sb.toString();
        super.isParse = true;
    }

}
