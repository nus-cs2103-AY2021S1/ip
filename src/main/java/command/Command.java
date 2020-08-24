package command;

import util.Storage;
import util.TaskList;
import util.Ui;

public abstract class Command {
    public abstract void execute(TaskList lst, Ui ui, Storage storage);
}
