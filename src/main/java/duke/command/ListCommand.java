package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand implements Command {

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
        return true;
    }
}
