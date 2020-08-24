package taskbot.command;

import taskbot.exceptions.TaskbotException;
import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;
    public DeleteCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskbotException {
        taskList.deleteTask(taskIndex);
    }
}
