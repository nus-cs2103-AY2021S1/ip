package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ExitCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.exit();
    }

    public boolean isExit() {
        return true;
    }
}
