package clippy.command;

import clippy.exception.ClippyException;

import clippy.storage.Storage;

import clippy.task.TaskList;

import clippy.ui.Ui;

/**
 * Represents a command that can be executed on TaskList, Ui and Storage objects.
 */
public abstract class Command {
    /**
     * Returns resulting message to be displayed by GUI from the execution of the command.
     * 
     * @param tasks TaskList object used in the current Clippy session.
     * @param ui Ui object used in the current Clippy session.
     * @param storage Storage object used in the current Clippy session.
     * @return Resulting message to be displayed by GUI as a result of the command execution.
     * @throws ClippyException If command cannot be executed due to e.g. incorrect format of user input.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws ClippyException;
}
