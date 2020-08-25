package duke.command;

import duke.storage.Storage;
import duke.util.TaskList;
import duke.Ui;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);


}
