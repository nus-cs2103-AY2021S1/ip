package duke.command;

import duke.common.CustomException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.IOException;

public abstract class Command {
    public abstract void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException, IOException;

    public boolean isExit() {
        return false;
    }
}
