package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {

    private int taskDone;

    public DoneCommand(int taskDone) {
        this.taskDone = taskDone;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.completeTask(taskDone);
        storage.writeData(tasks);
        ui.printMessage(task.toString());
    }
}
