package commands;

import service.DukeResponse;
import service.DukeService;

public class ListCommand extends Command {
    public static final String commandWord = "list";
    public ListCommand(String raw) {
        super(raw);
    }

    @Override
    public DukeResponse execute(DukeService service) {
        return service.getAllJobs();
    }

    @Override
    public void parse() throws Exception {
    }
}
