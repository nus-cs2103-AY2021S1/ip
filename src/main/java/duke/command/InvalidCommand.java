package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class InvalidCommand extends Command {

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
        throw new DukeException("Invalid Command.");
    }
}
