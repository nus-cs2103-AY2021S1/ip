package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * Represents a list command.
 */
public class ListCommand implements Command {

    /**
     * Shows all the tasks that are in the list.
     *
     * @param ts
     * @param ui
     * @param input
     */
    @Override
    public void execute(TaskList ts, Ui ui, String input) {
        ui.list();
    }
}
