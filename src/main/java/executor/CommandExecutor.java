package executor;

import command.Command;
import tasklist.TaskList;

/**
 * Represents executor of add command.
 * Executes the command given to Duke.
 */
public abstract class CommandExecutor {
    public abstract void execute(Command command, TaskList taskList);
}
