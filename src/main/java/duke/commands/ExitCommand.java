package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand implements Command {

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.farewell();
        return true;
    }
}
