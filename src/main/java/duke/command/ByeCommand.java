package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        this.isExit = true;
        ui.exit();
        storage.update();
    }
}
