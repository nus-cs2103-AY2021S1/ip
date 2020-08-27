package duke.operation;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The operation that changes a specified <code>Task</code> into completed.
 */
public class DoneOperation extends Operation {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructor method.
     * @param taskList the <code>TaskList</code> containing the <code>Task</code> that is to be changed.
     * @param index the associated index of the <code>Task</code>.
     */
    public DoneOperation(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Specifies that this is not an <code>ExitOperation</code>.
     * @return <code>false</code>.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the operation by converting the specified <code>Task</code> into completed.
     * @return a <code>String</code> indicating the <code>Task</code> has been completed.
     */
    @Override
    public String execute() {
        Task completed = this.taskList.completeTask(this.index);
        return "You have completed this task:\n" + completed;
    }
}
