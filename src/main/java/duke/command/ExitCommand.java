package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

public class ExitCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage store) throws DukeException {
        System.exit(0);
        return "";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
