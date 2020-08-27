package duke.command;

import duke.component.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.component.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> listOfTasks = taskList.getList();
        ui.listAllTasks(listOfTasks);
    }
}
