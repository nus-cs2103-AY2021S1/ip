package dukechatbot.executor;

import dukechatbot.command.Command;
import dukechatbot.tasklist.TaskList;

/**
 * Represents executor of a command.
 * Executes the command given to Duke.
 */
public interface CommandExecutor {
    String execute(Command command, TaskList taskList);
}
