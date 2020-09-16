package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * Represents a list command to list the current stored tasks.
 */
public class ListCommand extends Command {

    /**
     * Initializes a list command.
     */
    public ListCommand() {
    }

    /**
     * Lists the current stored tasks.
     *
     * @param taskList The existing task list.
     * @param ui       The UI instance which handles Duke's user interface.
     * @param storage  The existing storage for Duke.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listStoredTasks(taskList.getStoredTasks());
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
