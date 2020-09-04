package main.command;

import main.exception.InvalidTaskException;
import main.task.Task;
import main.task.TaskList;
import main.ui.Ui;

/**
 * Represents the delete command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
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
     * @return the string indicating the task has been removed.
     * @throws InvalidTaskException if the taskNum is outside of the size of the task list.
     */
    @Override
    public String execute(Ui ui, TaskList tasks) throws InvalidTaskException {
        boolean isInvalidIndex = taskNum < 1 || taskNum > tasks.size();

        if (isInvalidIndex) {
            throw new InvalidTaskException();
        }

        Task removedTask = tasks.remove(taskNum - 1);

        return ui.printRemoveSuccess(removedTask, tasks.size());
    }

    /**
     * Returns true since there can still be commands after this.
     * @return true.
     */
    @Override
    public boolean hasCommandAfter() {
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
