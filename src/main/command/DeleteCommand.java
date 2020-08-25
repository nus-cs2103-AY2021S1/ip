package main.command;

import main.task.Task;
import main.task.TaskList;
import main.ui.Ui;
import main.exception.InvalidTaskException;

/**
 * Represents the delete command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
 * @since v0.1
 */
public class DeleteCommand implements Command {
    private final int taskNum;

    /**
     * Constructs a DeleteCommand instance with the index of the task
     * in the list.
     * @param taskNum the index of the task in the task list.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Marks the task at the index of the task list indicated by the taskNum
     * as done.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     * @throws InvalidTaskException if the taskNum is outside of the size of the task list.
     */
    @Override
    public void execute(Ui ui, TaskList tasks) throws InvalidTaskException {
        if (taskNum < 1 || taskNum > tasks.size())
            throw new InvalidTaskException();
        Task removed = tasks.remove(taskNum - 1);
        ui.printRemoveSuccess(removed, tasks.size());
    }

    /**
     * Returns true since there can still be commands after this.
     * @return true.
     */
    @Override
    public boolean hasCommand() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DeleteCommand) {
            DeleteCommand o = (DeleteCommand) obj;
            return this.taskNum == o.taskNum;
        }
        return false;
    }
}
