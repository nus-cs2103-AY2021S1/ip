package duke.Commands;

import duke.Storage.Storage;
import duke.Ui.Ui;
import duke.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printAllTasks(taskList);
    }
}


