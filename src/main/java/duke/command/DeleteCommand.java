package duke.command;

import duke.Storage;
import duke.exception.InvalidTaskException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.DukeMessages;

/**
 * Represents a Command given by the user to remove a Task from the TaskList.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Creates a DeleteCommand.
     * @param index An integer value representing the index of the Task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand.
     * @param list A TaskList containing the user's Tasks.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     * @return A String that contains a user message indicating that the specified task has been deleted.
     * @throws InvalidTaskException If details provided of Task to be removed are invalid.
     */
    @Override
    public String execute(TaskList list, Storage storage) throws InvalidTaskException {
        Task deletedTask = list.deleteTask(index);
        storage.writeToTaskStorage(list.getSaveString());
        return DukeMessages.printDeleteTaskMessage(deletedTask, list.getTaskListSize());
    }
}
