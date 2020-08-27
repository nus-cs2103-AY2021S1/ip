package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.component.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

    /**
     * Executes command, main logic for creating a new task.
     * @param taskList list of tasks.
     * @param ui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> listOfTasks = taskList.getList();
        ui.listAllTasks(listOfTasks);
    }
}
