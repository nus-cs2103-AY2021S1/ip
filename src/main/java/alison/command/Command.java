package alison.command;

import alison.exception.AlisonException;
import alison.tool.Storage;
import alison.tool.TaskList;
import alison.tool.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws AlisonException;

    public boolean isExit() {
        return false;
    }

}
