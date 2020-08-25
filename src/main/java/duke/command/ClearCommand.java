package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ClearCommand extends Command {
    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        taskItems.clearAll();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
