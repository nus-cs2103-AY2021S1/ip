package butler.command;

import butler.exception.ButlerException;
import butler.io.Storage;
import butler.io.Ui;
import butler.task.TaskList;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui Ui, Storage storage) throws ButlerException;

    public abstract Boolean isExit();
}
