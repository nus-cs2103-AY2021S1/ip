package duke.Command;

import duke.*;
import duke.Task.TaskList;
import duke.Ui.Ui;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
