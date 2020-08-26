package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbyeUser();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
