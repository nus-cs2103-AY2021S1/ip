package duke.command;

import duke.storage.*;
import duke.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isDone();

}
