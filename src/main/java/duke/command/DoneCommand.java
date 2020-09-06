package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents the done command when user wants to mark task as complete from task list.
 */
public class DoneCommand extends Command {
    private int taskIndex;

    /**
     * Initializes the DoneCommand Object.
     * @param taskIndex task to mark as complete.
     */
    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Sets task status to complete.
     * Renders task complete message upon successful update.
     * Updates local storage with updated task list.
     *
     * @param taskItems represents the tasks which is added to the task list.
     * @param ui        ui component which user interacts with or sees.
     * @param storage   Object for saving and loading tasks list to hard disk.
     * @throws duke.DukeException if task number specified by user does not exist.
     */
    @Override
    public String execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = taskItems.getTask(taskIndex);
            task.markDone();
            storage.saveTaskToMemory(taskItems.getAll());
            return ui.doneTaskReply(task);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number does not exist");
        }
    }

    /**
     * Returns instruction to Duke class whether to terminate program.
     *
     * @return bool value to not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
