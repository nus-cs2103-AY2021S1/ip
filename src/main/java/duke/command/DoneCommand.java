package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * Represents a done command to mark a task as complete.
 */
public class DoneCommand extends Command {

    private int taskNumber;

    /**
     * Initializes a done command.
     *
     * @param taskNumber The task number of task to be marked as complete.
     */
    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Marks a task as complete, prints the done message, then updates the tasks in the storage.
     *
     * @param taskList The existing task list.
     * @param ui       The UI instance which handles Duke's user interface.
     * @param storage  The existing storage for Duke.
     * @throws DukeException When an error occurs while marking the task as complete.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.processDoneMessage(taskList.markTaskAsDone(taskNumber));
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
