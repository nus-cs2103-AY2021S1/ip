package commands;

import data.TaskList;
import data.task.Task;
import ui.Ui;
import utils.Messages;

/**
 * Represents an executable command.
 */
public abstract class Command {
    protected TaskList taskList;
    private int targetIndex = -1;

    public Command(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    protected Command() {}

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of tasks.
     *
     * @param taskList used to generate summary
     * @return summary message for tasks displayed
     */
    public static String getMessageForTaskListSummary(TaskList taskList) {
        return String.format(Messages.MESSAGE_TASK_LISTED_OVERVIEW_FORMAT, taskList.size());
    }

    /**
     * Extracts the the target task in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected Task getTargetTask() throws IndexOutOfBoundsException {
        return taskList.get(this.targetIndex - Ui.DISPLAYED_INDEX_OFFSET);
    }

    /**
     * Executes the command and returns the result.
     */
    abstract public CommandResult execute();
}
