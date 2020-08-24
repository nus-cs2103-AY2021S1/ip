package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Implements the Command object created when user quits the application
 */
public class ByeCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Shows close message
     *
     * @param tasks   current task list
     * @param ui      text ui interface
     * @param storage storage file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showCloseMessage();
    }
}
