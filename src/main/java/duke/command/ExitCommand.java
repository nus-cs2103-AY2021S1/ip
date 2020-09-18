package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Creates an exit command.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command.
     *
     * @param tasks   The list of existing tasks.
     * @param ui      The ui that handles user interaction.
     * @param storage The storage that stores the list of existing tasks.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showByeMessage();
    }

    /**
     * Determines if the command is an exit command.
     *
     * @return Always true.
     */
    public boolean isExit() {
        return true;
    }

}
