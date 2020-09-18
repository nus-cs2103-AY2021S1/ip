package luke.commands;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;
import luke.exception.LukeIndexOutOfBoundsException;
import luke.task.Task;

/**
 * Represents a command to delete existing tasks.
 */
public class DeleteCommand extends Command {

    protected int taskNumber;

    /**
     * Creates a DeleteCommand to delete the given task.
     *
     * @param taskNumber index number of the task to be deleted
     */
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
        } catch (NullPointerException e) {
            throw new LukeIndexOutOfBoundsException(String.valueOf(this.taskNumber + 1));
        }
    }
}
