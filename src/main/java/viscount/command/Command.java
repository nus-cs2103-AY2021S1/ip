package viscount.command;

import viscount.*;

import viscount.exception.ViscountException;

/**
 * Represents a command.
 */
public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws ViscountException;
    
    public abstract boolean isExit();
}
