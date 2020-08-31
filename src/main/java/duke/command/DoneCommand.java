package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskException;
import duke.exception.StorageException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a Command given by the user to mark a Task as done.
 */
public class DoneCommand extends Command {
    private int taskIndex;

    /**
     * Creates a DoneCommand.
     * @param taskIndex An integer value representing the index of the Task to be marked completed.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the DoneCommand.
     * First, the Task specified is marked as done, and the updated TaskList is written to local storage.
     * Lastly, a message is printed to indicate that the task has been marked as completed successfully.
     * @param list A TaskList containing the user's Tasks.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     * @throws InvalidTaskException if details provided of Task to be marked done are invalid.
     * @throws StorageException if the Tasks cannot be written to local storage.
     */
    @Override
    public void execute(TaskList list, Storage storage) throws InvalidTaskException, StorageException {
        Task doneTask = list.completeTask(this.taskIndex);
        storage.writeTaskStorage(list.getSaveString());
        Ui.doneTaskMessage(doneTask);
    }
}
