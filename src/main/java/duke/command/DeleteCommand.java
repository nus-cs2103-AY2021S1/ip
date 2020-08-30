package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskException;
import duke.exception.StorageException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a Command given by the user to remove a Task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a DeleteCommand.
     * @param index An integer value representing the index of the Task to be deleted.
     */
    public DeleteCommand(int index){
        this.index = index;
    }

    /**
     * Executes the DeleteCommand.
     * First, the Task specified is removed, and the updated TaskList is written to local storage.
     * Lastly, a message is printed to indicate that the task has been removed successfully.
     * @param list A TaskList containing the user's Tasks.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     * @throws InvalidTaskException if details provided of Task to be removed are invalid.
     * @throws StorageException if the Tasks cannot be written to local storage.
     */
    @Override
    public void execute(TaskList list, Storage storage) throws InvalidTaskException, StorageException {
        Task deletedTask = list.deleteTask(index);
        storage.writeTaskStorage(list.getSaveString());
        Ui.deleteTaskMessage(deletedTask, list.taskListSize());
    }
}
