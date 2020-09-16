package duke.command;

import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;

/**
 * Represents a command which is used exit the program.
 * It is executed when the execute method is called.
 *
 * @author Roger Lim
 */

public class ExitCommand extends Command {

    /**
     * Executes the delete command.
     * UI will run bye method and the system would terminate.
     * @param tasks The task list of the system.
     * @param ui The UI of the system which interacts with user.
     * @param storage The storage of the system.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
