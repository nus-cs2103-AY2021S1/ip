package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a Bye message action.
 */
public class ByeCommand implements Command {

    /**
     * Performs the printing of bye message on Ui.
     *
     * @param tasks The TaskList for Duke.
     * @param ui The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return False because Duke should stop running.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printBye();
        return false;
    }
}
