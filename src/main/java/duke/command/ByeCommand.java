package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which ends operation.
 */
public class ByeCommand extends Command {

    /**
     * Indicates whether operation should continue running.
     *
     * @return false.
     */
    @Override
    public boolean isRunning() {
        return false;
    }

    /**
     * Ends the current operation.
     *
     * @param taskList TaskList associated with the operation.
     * @param ui Ui responsible for the operation.
     * @param storage Storage associated with the operation.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.exit();
    }
}
