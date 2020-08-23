package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    int deleteIndex;

    public DeleteCommand(int taskDone) {
        this.deleteIndex = taskDone;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.removeATask(deleteIndex);
        storage.writeData(tasks);
        ui.printMessage("I have removed this task");
        ui.printMessage(task.toString());
        ui.printMessage("You now have " + tasks.getNumberOfTasks() + " tasks left!");
    }
}
