package duckie.command;

import duckie.task.TaskList;
import duckie.Ui;
import duckie.Storage;

public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showEnding();
    }
}
