package duke.commands;

import duke.*;
import duke.tasks.TaskList;

public class ByeCommand extends Command {
    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        ui.showExit();
    }

    public boolean isExit() {
        return true;
    }
}
