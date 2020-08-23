package duke.operation;

import duke.task.Task;
import duke.task.TaskList;

/**
 * The operation that deletes a Task from the TaskList.
 */
public class DeleteOperation extends Operation {
    private final TaskList taskList;
    private final int index;

    /**
     * Constructor method.
     * @param taskList taskList the TaskList containing the Task that is to be removed.
     * @param index the associated index of the Task.
     */
     public DeleteOperation(TaskList taskList, int index) {
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
     * Removes the given Task from the TaskList.
     * @return a String indicating the task that has been removed.
     */
    @Override
    public String execute() {
        Task removed = this.taskList.removeTask(this.index);
        String status = "Noted. I've removed this task:\n"
                + removed + "\n"
                + String.format("You now have %d tasks in the list", this.taskList.getCurrCapacity());
        return status;
    }
}
