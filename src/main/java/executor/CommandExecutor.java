package executor;

import command.Command;
import tasklist.TaskList;

public abstract class CommandExecutor {
    public abstract void execute(Command command, TaskList taskList);
}
