package command;

import taskList.TaskList;
import storage.Storage;
import exception.DukeException;

/**
 * Abstract class for handling different commands.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
