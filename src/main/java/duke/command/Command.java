package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public boolean isExit() {
        return false;
    }

    public abstract String execute(TaskList tasks, Ui ui, Storage storage);
}
