package duke.command;

import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Storage storage, Ui ui);

}
