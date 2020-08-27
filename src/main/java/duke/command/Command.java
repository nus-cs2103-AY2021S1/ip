package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;

public abstract class Command {
    public abstract boolean isExit();

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
