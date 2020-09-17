package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidTaskException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to delete a task from the user's list of tasks.
 */
public class DeleteCommand extends Command {

    /** Integer value representing the ID of the task to be deleted. */
    private final int taskID;

    /**
     * Creates and initialises a new DeleteCommand object.
     *
     * @param taskID int value representing the ID of the task to be deleted.
     */
    public DeleteCommand(int taskID) {
        this.taskID = taskID;
    }

    /**
     * Deletes the task from the user's list of tasks and updates the task list
     * stored in the designated file.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply for successful deletion of task.
     * @throws DukeException If the task could not be deleted due to invalid arguments.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskID > tasks.getListSize() || this.taskID <= 0) {
            String error = "Invalid Task! The task ID you provided is not valid.";
            throw new InvalidTaskException(error);
        }
        Task deletedTask = tasks.getTask(this.taskID - 1);
        tasks.removeTask(this.taskID - 1);
        storage.saveToFile(tasks);
        return ui.showDeletedTask(deletedTask, tasks.getListSize());
    }

    /**
     * Indicates if the Duke session has ended.
     *
     * @return False since the Duke session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
