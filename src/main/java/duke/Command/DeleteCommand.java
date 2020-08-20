package duke.Command;

import duke.*;
import duke.Exception.DukeException;
import duke.Task.Task;
import duke.Task.TaskList;
import duke.Ui.Ui;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.get(index);
        taskList.remove(index);
        storage.saveTasks(taskList);
        ui.showTaskDeletionMessage(task, taskList);
    }
}
