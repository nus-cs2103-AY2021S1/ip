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
     * @param ui Ui to show response of execution.
     * @return Response of execution.
     */
    public abstract String execute(Storage storage, TaskList tasks, Ui ui);
}
