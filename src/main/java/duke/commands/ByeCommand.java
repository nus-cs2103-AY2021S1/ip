package duke.commands;

import duke.tasklist.TaskList;
import duke.ui.Ui;

/** Represents the command that displays the goodbye message to the user when executed. */
public class ByeCommand extends Command {

    /** Returns true to indicate that the program is exiting. */
    @Override
    public boolean isExit() {
        return true;
    }

    /** Displays the goodbye message to the user.
     *
     * @param taskList The taskList involved.
     * @param ui The ui that displays the goodbye message to the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showBye();
    }
}
