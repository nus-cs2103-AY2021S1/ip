package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;

public class ListCommand implements Command {
    @Override
    public String execute(TaskList tasks, Storage store) throws DukeException {
        return tasks.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
