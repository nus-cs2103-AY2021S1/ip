package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.model.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command{

    public ListCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        String[] commandDetails = command.split(" ", 2);
        if (commandDetails.length != 1) {
            throw new DukeException("List command should not include extra parameters!");
        }
        ArrayList<Task> taskList = tm.getTaskList();
        if (taskList.isEmpty()) {
            String s = "List is empty!";
            ui.showDetails(s);
        } else {
            ui.showTaskList(taskList);
        }
    }
}
