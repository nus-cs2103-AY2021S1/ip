package duke.command;

import duke.SaveManager;
import duke.TaskManager;
import duke.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(Ui ui, TaskManager taskManager, SaveManager saveManager) {
        ui.display(taskManager.toString());
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
