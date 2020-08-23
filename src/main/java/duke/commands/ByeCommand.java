package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showBye();
    }
}
