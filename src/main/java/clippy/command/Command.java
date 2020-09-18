package clippy.command;

import clippy.exception.ClippyException;
import clippy.storage.Storage;
import clippy.task.TaskList;
import clippy.ui.Ui;

public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ClippyException;
}
