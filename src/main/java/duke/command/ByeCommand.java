package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
