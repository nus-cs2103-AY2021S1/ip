package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;
import duke.logic.Storage;
import duke.task.TaskManager;
import duke.ui.Ui;

/**
 * Represents an invalid command.
 */
public class InvalidCommand extends Command {

    /**
     * The invalid command.
     */
    private final String command;

    /**
     * Constructs an invalid command.
     *
     * @param command The invalid command.
     */
    public InvalidCommand(String command) {
        this.command = command;
    }

    @Override
    public String execute(TaskManager manager, Ui ui, Storage storage) throws DukeException {
        throw new DukeInvalidCommandException(command);
    }
}
