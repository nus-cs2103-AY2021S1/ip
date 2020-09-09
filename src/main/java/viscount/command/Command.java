package viscount.command;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountException;

/**
 * Represents a command.
 */
public abstract class Command {
    public abstract String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws ViscountException;
}
