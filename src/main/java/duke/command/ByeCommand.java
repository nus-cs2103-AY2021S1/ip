package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super();
        this.isExit = true;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        storage.saveData(tasks.getTasks());
        ui.sayFarewell();
    }
}
