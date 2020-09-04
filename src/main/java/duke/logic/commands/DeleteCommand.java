package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.model.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeleteCommand extends Command{

    public DeleteCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        String[] commandDetails = command.split(" ", 2);
        try {
            int delIndex = Integer.parseInt(commandDetails[1]) - 1;
            Task delTask = tm.getTask(delIndex);
            tm.deleteTask(delIndex);
            ui.showDetails("Task deleted: " + delTask);
        } catch (NumberFormatException e) {
            throw new DukeException("Index is not a number!");
        }
    }
}
