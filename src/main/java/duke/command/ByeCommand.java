package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
