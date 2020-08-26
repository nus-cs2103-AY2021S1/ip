package duckie.command;

import duckie.task.TaskList;
import duckie.Ui;
import duckie.Storage;
import duckie.exception.DuckieException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException;

    public boolean isExit() {
        return false;
    }

}
