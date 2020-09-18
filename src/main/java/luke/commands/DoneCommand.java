package luke.commands;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;
import luke.exception.LukeIndexOutOfBoundsException;
import luke.task.Task;

/**
 * Represents a command to mark existing tasks as done.
 */
public class DoneCommand extends Command {

    protected int taskNumber;

    /**
     * Creates a DoneCommand to mark the given task as done.
     *
     * @param taskNumber index number of the task to be marked as done
     */
    public DoneCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws LukeException {
        try {
            Task doneTask = tasks.doTask(this.taskNumber);
            storage.save(tasks);
            return ui.showDoneResult(doneTask);
        } catch (NullPointerException e) {
            throw new LukeIndexOutOfBoundsException(String.valueOf(this.taskNumber));
        }
    }
}
