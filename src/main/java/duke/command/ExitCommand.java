package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Represents an exit command.
 */
public class ExitCommand implements Command {
    /**
     * Prints a goodbye message to the user.
     * 
     * @param tasks List of user's tasks.
     * @param ui UI of Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayGoodbye();
    }

    /**
     * Tells Duke to exit.
     * 
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
