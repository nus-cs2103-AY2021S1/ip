package duke.operation;

import duke.result.Result;
import duke.task.TaskList;

/**
 * The operation that lists all the <code>Tasks</code> in <code>TaskList</code>.
 */
public class ListOperation extends Operation {
    private final TaskList taskList;

    /**
     * Constructor method.
     * @param taskList the <code>TaskList</code> that is to be printed.
     */
    public ListOperation(TaskList taskList) {
        this.taskList = taskList;
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
     * Executes the operation by retrieving all <code>Tasks</code> in <code>TaskList</code>.
     * @return a <code>Result</code> containing all <code>Tasks</code> stored in <code>TaskList</code>.
     */
    @Override
    public Result execute() {
        String message = "Here are your tasks:\n" + this.taskList.toString();
        return new Result(true, message, this.isExit());
    }
}
