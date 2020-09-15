package luke.commands;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;

/**
 * Represents a command for Luke to execute.
 */
public abstract class Command {

    public abstract String execute(Storage storage, TaskList taskList, Ui ui) throws LukeException;

}
