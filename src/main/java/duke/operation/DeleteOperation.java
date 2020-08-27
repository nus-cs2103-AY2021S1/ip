package duke.operation;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The operation that deletes a <code>Task</code> from the <code>TaskList</code>.
 */
public class DeleteOperation extends Operation {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructor method.
     * @param taskList taskList the <code>TaskList</code> containing the
     *                 <code>Task</code> that is to be removed.
     * @param index the associated index of the <code>Task</code>.
     */
    public DeleteOperation(TaskList taskList, int index) {
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
     * Removes the given <code>Task</code> from the <code>TaskList</code>.
     * @return a <code>String</code> indicating the task that has been removed.
     */
    @Override
    public String execute() {
        Task removed = this.taskList.removeTask(this.index);
        return "Noted. I've removed this task:\n"
                + removed + "\n"
                + String.format("You now have %d tasks in the list", this.taskList.getCurrCapacity());
    }
}
