package duke.command;

import duke.task.Task;

/**
 * Generic modify task command which inherits from generic command class.
 * Parent to DoneCommand and DeleteCommand.
 * Takes in task to be modified as parameter.
 */
public abstract class AbstractModifyTaskCommand extends Command {
    protected final Task task;

    public AbstractModifyTaskCommand(Task task) {
        this.task = task;
    }
}
