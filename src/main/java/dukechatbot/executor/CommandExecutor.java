package dukechatbot.executor;

import dukechatbot.command.Command;
import dukechatbot.tasklist.TaskList;

/**
 * Represents executor of add command.
 * Executes the command given to Duke.
 */
public abstract class CommandExecutor {
    public abstract void execute(Command command, TaskList taskList);
}
