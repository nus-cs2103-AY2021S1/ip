package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;

/**
 * Encapsulates data and methods specific to invalid commands.
 */
public class InvalidCommand extends Command {

    /**
     * Creates a new instance of the Invalid command class.
     */
    public InvalidCommand() {
    }

    /**
     * Throws a DukeException stating that the command entered is invalid.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list that the operation was supposed to be done on.
     * @param ui UI object for the instance of Duke.
     * @throws DukeException Exception stating that an invalid command is used is thrown.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException {

        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Task list cannot be null";

        throw new DukeException("Invalid Command.");
    }
}
