package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class InvalidCommand extends Command {

    public InvalidCommand() {
    }

    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException {
        throw new DukeException("Invalid Command.");
    }
}
