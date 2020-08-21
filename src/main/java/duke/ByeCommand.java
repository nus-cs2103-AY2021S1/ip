package duke;

import duke.component.Storage;
import duke.component.Ui;

public class ByeCommand extends Command {
    public ByeCommand(String input) {
        super(input);
        isExit = true;
    }

    @Override
    public void execute(Ui ui, TaskList list, Storage storage) {
        ui.printList(list, t -> true, "");
    }
}
