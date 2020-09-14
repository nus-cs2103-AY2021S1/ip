package duke.commands;

import java.util.List;

import duke.data.TaskList;
import duke.data.task.Task;
import duke.ui.Ui;
import duke.utils.Messages;

/**
 * Represents an executable command.
 */
public abstract class Command {

    protected TaskList taskList;
    private int targetIndex = -1;

    /**
     * Constructs a {@code Command}.
     * @param targetIndex
     */
    public Command(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /** Constructs a {@code Command}. */
    protected Command() {}

    /**
     * Supplies the duke.data the command will operate on.
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
    public static String getMessageForTaskListSummary(List<Task> taskList) {
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
    public abstract CommandResult execute();

}
