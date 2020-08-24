package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList taskList, Storage storage, Ui ui);
    public abstract boolean isExit();
}
