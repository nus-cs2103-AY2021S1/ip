package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidTaskException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class DoneCommand extends Command {

    /** Integer value representing the ID of the task to be marked as done. */
    private final int taskID;

    /**
     * Creates and initialises a new DoneCommand object.
     *
     * @param taskID int value representing the ID of the task to be marked as done.
     */
    public DoneCommand(int taskID) {
        this.taskID = taskID;
    }

    /**
     * Marks the task as done and updates it accordingly in the user's list of tasks
     * stored in the designated file.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing the reply for successful completion of task.
     * @throws DukeException If the task could not be marked as done due to invalid arguments.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskID > tasks.getListSize() || this.taskID <= 0) {
            String error = "Invalid Task! The task ID you provided is not valid.";
            throw new InvalidTaskException(error);
        }
        tasks.completeTask(this.taskID - 1);
        storage.saveToFile(tasks);
        Task completedTask = tasks.getTask(this.taskID - 1);
        return ui.showCompletedTask(completedTask);
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
