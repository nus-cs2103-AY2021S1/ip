package duke.command;

import duke.Ui;
import duke.Storage;
import duke.TaskList;

public abstract class Command {

    public abstract boolean isExit();

    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
