package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * Represents a delete command to delete a particular stored task.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    /**
     * Initializes a delete command.
     *
     * @param taskNumber The task number of task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Deletes a task, prints the delete message, then updates the tasks in the storage.
     *
     * @param taskList The existing task list.
     * @param ui       The UI instance which handles Duke's user interface.
     * @param storage  The existing storage for Duke.
     * @throws DukeException When an error occurs while deleting the task.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.processDeleteMessage(taskList.deleteTask(taskNumber), taskList.getCount());
        storage.updateTasks(taskList);
    }

    /**
     * Returns if the program should continue running at the current point in time.
     * If not, the program should be exited.
     *
     * @return If the program should continue running.
     */
    @Override
    public boolean isInProgram() {
        return true;
    }
}
