package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {

    int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.getTask(index);
        taskList.removeTask(task);
        ui.printDeleteTask(taskList, task);
        storage.delete(task);
    }
}
