package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.UnknownCommandException;
import duke.ui.Ui;
import duke.ui.UiForGui;

/**
 * Encapsulates unknown commands for the Duke program. These are invalid commands (in which the first word of the
 * command is not any of the valid commands).
 */
public class UnknownCommand extends Command {

    /**
     * Executes the command in the CLI version of Duke and informs the user that the command entered was invalid.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(new UnknownCommandException());
    }

    /**
     * Executes the command in the GUI version of Duke and informs the user that the command entered was invalid.
     *
     * @param tasks The list of tasks in the program.
     * @param uiForGui The UiForGui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    @Override
    public String execute(TaskList tasks, UiForGui uiForGui, Storage storage) {
        return uiForGui.showError(new UnknownCommandException());
    }
}
