package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public abstract class Command {
    protected boolean isExit;
    protected Task task;

    protected Command() {
        this.isExit = false;
    }

    protected Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    public Task getTask() {
        return task;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
