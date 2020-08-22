package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand implements Command {

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.printExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
