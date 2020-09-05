package duke.operation;

import duke.list.TaskList;
import duke.result.Result;
import duke.task.Task;

/**
 * The operation that deletes a <code>Task</code> from the <code>TaskList</code>.
 */
public class DeleteOperation extends Operation {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructor method.
     *
     * @param taskList taskList the <code>TaskList</code> containing the
     *                 <code>Task</code> that is to be removed.
     * @param index the associated index of the <code>Task</code>.
     */
    public DeleteOperation(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Removes the given <code>Task</code> from the <code>TaskList</code>.
     *
     * @return a <code>Result</code> indicating the task that has been removed.
     */
    @Override
    public Result execute() {
        if (!this.taskList.isValidIndex(index)) {
            String message = "The index you have passed in cannot be found in the list of tasks.";
            return new Result(false, message, this.isExit());
        }
        Task removed = this.taskList.removeTask(this.index);
        String message = "Noted. I've removed this task:\n"
                + removed + "\n"
                + String.format("You now have %d tasks in the list", this.taskList.getCurrCapacity());
        return new Result(true, message, this.isExit());
    }
}
