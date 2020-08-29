package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;
import duke.exceptions.NoSuchTaskException;

/**
 * Command to delete a Task. Created by using "delete taskNumber"
 */
public class DeleteCommand extends Command {

    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Delete the task with the specified taskNumber.
     *
     * @param tasks task list containing all tasks
     * @param ui ui for interaction with user
     * @param storage storage to retrieve and store tasks entered by user
     * @throws NoSuchTaskException if invalid taskNumber was provided
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) throws NoSuchTaskException {
        Task deletedTask = tasks.deleteTask(taskNumber);
        String message = ui.formatMessage(String.format("Okay, I've deleted the following task: \n %s",
                deletedTask.toString()));
        storage.updateTasks(tasks.getListOfTasks());
        return new Response(false, message);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DeleteCommand) {
            return this.taskNumber == ((DeleteCommand) other).taskNumber;
        } else {
            return false;
        }
    }
}
