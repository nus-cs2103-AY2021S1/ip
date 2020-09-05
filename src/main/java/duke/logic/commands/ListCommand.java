package duke.logic.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.model.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command {

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
        String s = "";
        if (taskList.isEmpty()) {
            s = "List is empty!";
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                s = s + (i + 1) + ". " + taskList.get(i) + "\n";
            }
        }
        ui.showDetails(s);
    }
}
