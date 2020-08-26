package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {
    

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.storelist(tasks);
        ui.farewell();
        
    }

    @Override
    public boolean continueRunning() {
        return false;
    }
}
