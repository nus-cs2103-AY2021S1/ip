package command;

import service.DukeService;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public void execute(DukeService dukeService) {
        dukeService.printList();
    }
}
