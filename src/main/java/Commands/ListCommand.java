package Commands;

import Storage.Storage;
import Ui.Ui;
import task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printAllTasks(taskList);
    }
}


