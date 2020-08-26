package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;

public abstract class Command {
    protected String commandName;
    protected boolean isExit;

    public boolean isExit() {
        return isExit;
    }

    abstract public void execute(TaskList list, Ui ui, Storage storage) throws DukeException;
}
