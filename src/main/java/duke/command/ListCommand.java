package duke.command;

import duke.Storage;
import duke.tool.TaskList;
import duke.ui.Ui;

public class ListCommand implements Command {

    @Override
    public void excute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
