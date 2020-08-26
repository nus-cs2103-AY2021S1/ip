package alice.command;

import alice.AliceException;
import alice.storage.Storage;
import alice.task.TaskList;
import alice.ui.Ui;

public abstract class Command {

    public abstract void process(TaskList tasks, Ui ui, Storage storage) throws AliceException;

    public boolean isExitCommand() {
        return false;
    }
}
