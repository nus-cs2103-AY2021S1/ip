package command;

import service.DukeService;

public class DoneCommand extends Command {
    public DoneCommand(String input) {
        super(input);
    }

    @Override
    public void execute(DukeService dukeService) {
        dukeService.handleDoneCommand(input);
    }
}
