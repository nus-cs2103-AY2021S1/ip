package duke.operation;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The operation that changes a specified Task into completed.
 */
public class DoneOperation extends Operation {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructor method.
     * @param taskList the TaskList containing the Task that is to be changed.
     * @param index the associated index of the Task.
     */
    public DoneOperation(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Specifies that this is not an ExitOperation.
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the Done Operation by converting the specified Task into completed.
     * @return a String indicating the Task has been completed.
     */
    @Override
    public String execute() {
        Task completed = this.taskList.completeTask(this.index);
        return "You have completed this task:\n" + completed;
    }
}
