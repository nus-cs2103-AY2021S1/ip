package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    @Override
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.showExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
