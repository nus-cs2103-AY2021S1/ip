package main.command;

import main.exception.InvalidTaskException;
import main.task.Task;
import main.task.TaskList;
import main.ui.Ui;

/**
 * Represents the done command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.1
 */
public class DoneCommand implements Command {
    private final int taskNum;

    /**
     * Constructs a DoneCommand instance with the index of the task
     * in the list.
     * @param taskNum the index of the task in the task list.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    /**
     * Returns the string indicating the task has been successfully marked as done.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     * @return the string indicating the task has been successfully marked as done.
     * @throws InvalidTaskException if the taskNum is outside of the size of the task list.
     */
    @Override
    public String execute(Ui ui, TaskList tasks) throws InvalidTaskException {
        boolean isInvalidIndex = taskNum < 1 || taskNum > tasks.size();

        if (isInvalidIndex) {
            throw new InvalidTaskException();
        }

        Task task = tasks.get(taskNum - 1);
        task.setDone();

        return ui.printDoneSuccess(task);
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
        if (obj instanceof DoneCommand) {
            DoneCommand o = (DoneCommand) obj;
            return this.taskNum == o.taskNum;
        }
        return false;
    }
}
