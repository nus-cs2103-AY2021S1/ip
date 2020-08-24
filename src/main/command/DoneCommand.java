package main.command;

import main.task.Task;
import main.task.TaskList;
import main.ui.Ui;
import main.exception.InvalidTaskException;

public class DoneCommand implements Command {
    private final int taskNum;

    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(Ui ui, TaskList tasks) throws InvalidTaskException {
        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new InvalidTaskException();
        }
        Task task = tasks.get(taskNum - 1);
        task.setDone();
        ui.printDoneSuccess(task);
    }

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
