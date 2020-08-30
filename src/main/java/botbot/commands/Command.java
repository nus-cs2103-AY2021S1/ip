package botbot.commands;

import botbot.Storage;
import botbot.TaskList;
import botbot.Ui;

/**
 * Represents a command with the ability to be executed.
 */
public abstract class Command {
    /**
     * Executes the command.
     *
     * @param storage Storage to save task list to.
     * @param tasks Task list to execute command on.
     * @param ui Ui to print status of execution.
     */
    public abstract void execute(Storage storage, TaskList tasks, Ui ui);
}
