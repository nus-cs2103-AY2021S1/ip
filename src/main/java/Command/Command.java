package command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

public abstract class Command {
    public Task task;
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
