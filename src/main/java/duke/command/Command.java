package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public abstract class Command {
    protected boolean isExit;
    private int taskIndex;

    public Command() {
        this.isExit = false;
        this.taskIndex = -1;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException;

}