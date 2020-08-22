package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class InvalidCommand extends Command {
    public boolean isExit() {
        return false;
    }

    public void execute(String input, TaskList taskList, Storage storage) throws DukeException {
        throw new DukeException("\tOops! I'm not sure what you meant!\n"
                + "\tPlease try again!");
    }
}
