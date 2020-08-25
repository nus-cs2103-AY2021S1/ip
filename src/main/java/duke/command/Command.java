package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public abstract class Command {
    protected boolean exit;

    public boolean isExit() {
        return this.exit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);
}
