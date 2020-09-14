package luke.commands;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;
import luke.exception.LukeIndexOutOfBoundsException;
import luke.task.Task;

public class DeleteCommand extends Command {

    protected int taskNumber;

    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws LukeException {
        try {
            Task deletedTask = tasks.deleteTask(this.taskNumber);
            storage.save(tasks);
            return ui.showDeleteResult(deletedTask, tasks.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new LukeIndexOutOfBoundsException(String.valueOf(this.taskNumber + 1));
        }
    }
}
