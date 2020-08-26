package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {

    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks.getPlanner());
        return false;
    }
}
