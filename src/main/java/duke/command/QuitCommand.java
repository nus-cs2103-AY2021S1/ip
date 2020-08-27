package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a quit command.
 */
public class QuitCommand implements Command {

    /**
     * Shows a goodbye message before exiting the program.
     *
     * @param ts
     * @param ui
     * @param input
     */
    @Override
    public void execute(TaskList ts, Ui ui, String input) {
        ui.quit();
    }
}
