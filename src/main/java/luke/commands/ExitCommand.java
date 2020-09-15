package luke.commands;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Creates an ExitCommand to exit the program.
     */
    public ExitCommand() {
        super();
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws LukeException {
        System.exit(0);
        return ui.showExitResult();
    }
}
