package main.command;

import main.task.Task;
import main.task.TaskList;
import main.ui.Ui;
import main.exception.InvalidTaskException;

/**
 * Represents the done command.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.1
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
     * Removes the task at the index of the task list indicated by the taskNum.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     * @throws InvalidTaskException if the taskNum is outside of the size of the task list.
     */
    @Override
    public void execute(Ui ui, TaskList tasks) throws InvalidTaskException {
        if (taskNum < 1 || taskNum > tasks.size())
            throw new InvalidTaskException();
        Task task = tasks.get(taskNum - 1);
        task.setDone();
        ui.printDoneSuccess(task);
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
        if (obj instanceof DoneCommand) {
            DoneCommand o = (DoneCommand) obj;
            return this.taskNum == o.taskNum;
        }
        return false;
    }
}
