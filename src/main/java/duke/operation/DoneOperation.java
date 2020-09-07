package duke.operation;

import duke.list.TaskList;
import duke.result.Result;
import duke.task.Task;

/**
 * The operation that changes a specified <code>Task</code> into completed.
 */
public class DoneOperation extends Operation {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructor method.
     *
     * @param taskList the <code>TaskList</code> containing the <code>Task</code> that is to be changed.
     * @param index the associated index of the <code>Task</code>.
     */
    public DoneOperation(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Executes the operation by converting the specified <code>Task</code> into completed.
     *
     * @return a <code>Result</code> indicating if the <code>Task</code> has been completed.
     */
    @Override
    public Result execute() {
        if (!taskList.isValidIndex(index)) {
            String message = "The index you have passed in cannot be found in the list of tasks.";
            return new Result(false, message, this.isExit());
        }
        Task completed = taskList.completeTask(index);
        String message = "You have completed this task:\n" + completed;
        return new Result(true, message, this.isExit());
    }
}
