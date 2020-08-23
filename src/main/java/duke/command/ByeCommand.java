package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the Command to exit Duke.
 */
public class ByeCommand implements Command {

    /**
     * Prints the exit message before exiting Duke.
     *
     * @param ui      The ui of Duke.
     * @param storage The storage object.
     * @param tasks   The taskList.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        ui.printExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
