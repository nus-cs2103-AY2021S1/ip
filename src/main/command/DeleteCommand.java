package main.command;

import main.task.Task;
import main.task.TaskList;
import main.ui.Ui;
import main.exception.InvalidTaskException;

public class DeleteCommand implements Command {
    private final int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(Ui ui, TaskList tasks) throws InvalidTaskException {
        if (taskNum < 1 || taskNum > tasks.size())
            throw new InvalidTaskException();
        Task removed = tasks.remove(taskNum - 1);
        ui.printRemoveSuccess(removed, tasks.size());
    }

    @Override
    public boolean hasCommand() {
        return true;
    }
}
