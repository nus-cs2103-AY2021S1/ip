package taskbot.command;

import taskbot.exceptions.InvalidIndexException;
import taskbot.exceptions.TaskAlreadyCompleteException;
import taskbot.exceptions.TaskbotException;
import taskbot.task.TaskList;
import taskbot.ui.Ui;

public class DoneCommand extends Command {
    private int taskIndex;
    public DoneCommand(int taskIndex) {
        super(false);
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws TaskbotException {
        taskList.completeTask(taskIndex);
    }
}
