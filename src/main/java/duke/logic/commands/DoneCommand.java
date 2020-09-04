package duke.logic.commands;


import duke.exceptions.DukeException;
import duke.model.TaskManager;
import duke.model.task.Task;
import duke.storage.Storage;
import duke.ui.Ui;

public class DoneCommand extends Command{

    public DoneCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskManager tm, Ui ui, Storage storage) throws DukeException {
        String[] commandDetails = command.split(" ", 2);
        try {
            int doneIndex = Integer.parseInt(commandDetails[1]) - 1;
            Task doneTask = tm.getTask(doneIndex);
            tm.markTaskDone(doneIndex);
            ui.showDetails("Task marked as done: " + doneTask);
        } catch (NumberFormatException e) {
            throw new DukeException("Index is not a number!");
        }
    }
}
