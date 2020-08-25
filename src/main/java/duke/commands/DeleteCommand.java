package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.DukeInvalidIndexException;
import duke.task.Task;

public class DeleteCommand implements Command {
    public Integer index;

    public DeleteCommand(Integer index) {
        this.index = index;
    }
    public boolean execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= tasks.getSize() || index < 0) {
            throw new DukeInvalidIndexException();
        }
        Task currentTask = tasks.getTask(index);
        tasks.deleteTask(index);
        ui.deleteMessage(currentTask, tasks.getSize());
        storage.save(tasks.getPlanner());
        return false;
    }

}
