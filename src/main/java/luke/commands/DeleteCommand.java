package luke.commands;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;
import luke.task.Task;

public class DeleteCommand extends Command {

    protected int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws LukeException {
        try {
            Task deletedTask = tasks.deleteTask(this.taskNumber);
            storage.save(tasks);
            return ui.showDeleteResult(deletedTask, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new LukeException("Please enter a valid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
