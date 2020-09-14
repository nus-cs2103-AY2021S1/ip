package duke.command;

import duke.backend.Storage;
import duke.task.TaskList;
import duke.response.Ui;

/**
 * Represents a Bye message action.
 */
public class ByeCommand implements Command {
    /**
     * Returns true because command exits.
     *
     * @return true.
     */
    @Override
    public boolean shouldExit() {
        return true;
    }


    /**
     * Performs the printing of bye message on Ui.
     *
     * @param tasks The TaskList for Duke.
     * @param ui The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return False because Duke should stop running.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printBye();
    }
}
