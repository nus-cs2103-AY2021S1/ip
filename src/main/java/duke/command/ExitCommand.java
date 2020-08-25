package duke.command;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;
import duke.exception.DukeException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        ui.showExitMessage();
        storage.saveTasks(manager.getTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}