package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.UnknownCommandException;

/**
 * Encapsulates unknown commands for the Duke program. These are invalid commands (in which the first word of the
 * command is not any of the valid commands).
 */
public class UnknownCommand extends Command {

    /**
     * Executes the command and informs the user that the command entered was invalid.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(new UnknownCommandException());
    }
}
