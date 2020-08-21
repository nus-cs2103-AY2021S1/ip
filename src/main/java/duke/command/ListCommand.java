package duke.command;

import duke.component.*;

public class ListCommand extends Command {
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public void execute(Ui ui, TaskList list, Storage storage) {
        ui.printList(list, t -> true, "");
    }
}
