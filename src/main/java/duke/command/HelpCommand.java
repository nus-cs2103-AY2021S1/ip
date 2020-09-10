package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a help command
 */
public class HelpCommand implements Command {

    /**
     * Shows the available commands.
     *  @param ts
     * @param ui
     * @param input
     * @return
     */
    @Override
    public String execute(TaskList ts, Ui ui, String input) {
        return ui.help();
    }
}
