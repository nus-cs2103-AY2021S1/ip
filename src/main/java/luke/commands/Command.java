package luke.commands;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;

public abstract class Command {

    public abstract String execute(Storage storage, TaskList taskList, Ui ui) throws LukeException;

    public abstract boolean isExit();

}
