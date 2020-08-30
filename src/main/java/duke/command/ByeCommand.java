package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFarewell();
        return "Farewell.";
    }
}
