package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        this.exit = true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printGoodbye();
    }
}
