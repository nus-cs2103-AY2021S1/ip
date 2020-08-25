package commands;

import data.TaskList;
import data.task.Task;
import ui.Ui;
import utils.Messages;

// Represents an executable command.
public abstract class Command {
    protected TaskList taskList;
    private int targetIndex = -1;

    public Command(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    protected Command() {}

    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    public static String getMessageForTaskListSummary(TaskList taskList) {
        return String.format(Messages.MESSAGE_TASK_LISTED_OVERVIEW_FORMAT, taskList.size());
    }

    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return taskList.get(this.targetIndex - Ui.DISPLAYED_INDEX_OFFSET);
    }

    abstract public CommandResult execute();
}
