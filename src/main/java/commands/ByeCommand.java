package commands;

import service.DukeResponse;
import service.DukeService;

public class ByeCommand extends Command {
    public static final String commandWord = "bye";
    private static final String BYE_STATEMENT = "Bye. Hope to see you again soon!\n";
    public ByeCommand(String raw) {
        super(raw);
    }

    @Override
    public DukeResponse execute(DukeService service) {
        return new DukeResponse(BYE_STATEMENT);
    }

    @Override
    public void parse() throws Exception {
    }
}
