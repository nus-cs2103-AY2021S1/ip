package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;
import duke.exceptions.NoSuchTaskException;

/**
 * Command to mark a Task as complete. Created by using "done taskNumber"
 */
public class DoneCommand extends Command {

    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks the task with the specified taskNumber as completed in the TaskList, formats a feedback String to be
     * displayed to user and updates the Storage.
     *
     * @param tasks TaskList containing all tasks
     * @param ui Ui for formatting of message Strings to be displayed to user
     * @param storage Storage to retrieve and store Tasks entered by user
     * @throws NoSuchTaskException if invalid taskNumber was provided
     * @return Response object containing the feedback String to be displayed by the GUI
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) throws NoSuchTaskException {
        Task completedTask = tasks.completeTask(taskNumber);
        String message = ui.formatMessage(String.format("Great! I've marked the following task as done: \n %s",
                completedTask));
        storage.updateTasks(tasks.getListOfTasks());
        return new Response(false, message);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof DoneCommand) {
            return this.taskNumber == ((DoneCommand) other).taskNumber;
        } else {
            return false;
        }
    }
}
