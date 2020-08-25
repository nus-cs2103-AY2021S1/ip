package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.listTasks(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
