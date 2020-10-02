package duke.command;

import java.io.IOException;

import duke.exceptions.NoSuchTaskException;
import duke.storage.DukeStateManager;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Command to mark a Task as complete. Created by using "done taskNumber"
 */
public class DoneCommand extends Command {

    private int taskNumber;

    /**
     * Constructs a DoneCommand which represents a Command to mark a Task with the given taskNumber as completed.
     *
     * @param taskNumber used to identify the Task to be mark as completed
     */
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
     * @param dukeStateManager DukeStateManager to manage the current state of Duke
     * @return Response object containing the formatted feedback String to be displayed by the GUI
     * @throws NoSuchTaskException if invalid taskNumber was provided
     * @throws IOException if there is an error with storing changes into storage file
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage, DukeStateManager dukeStateManager)
            throws NoSuchTaskException, IOException {
        Task completedTask = tasks.completeTask(taskNumber);
        String message = ui.formatMessage(String.format("Great! I've marked the following task as done: \n %s",
                completedTask));
        storage.updateTasks(tasks.getListOfTasks());

        this.storeState(dukeStateManager, tasks, storage);

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
