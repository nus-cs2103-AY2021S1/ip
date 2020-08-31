package duke.command;

import duke.Gui;
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
     * @param gui instance of Ui to deal with user interface.
     * @param storage to read / write to storage.
     */
    @Override
    public ArrayList<String> execute(TaskList taskList, Gui gui, Storage storage, ArrayList<String> responseList) {
        ArrayList<Task> listOfTasks = taskList.getList();
        responseList.addAll(gui.listAllTasks(listOfTasks));
        return responseList;
    }
}
