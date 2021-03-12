package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Terminates the program.
     *
     * @param taskList TaskList object.
     * @param ui User Interface object.
     * @param storage Storage object.
     * @return Response message to user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.goodbye();
    }

    /**
     * Returns true to terminate the program.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
