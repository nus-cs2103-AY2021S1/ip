package command;

import service.DukeService;

public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public void execute(DukeService dukeService) {
        dukeService.handleDeleteCommand(input);
    }
}
