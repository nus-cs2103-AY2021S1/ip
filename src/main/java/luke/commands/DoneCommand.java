package luke.commands;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.exception.LukeException;
import luke.task.Task;

public class DoneCommand extends Command {

    protected int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws LukeException {
        try {
            Task doneTask = tasks.doTask(this.taskNumber);
            storage.save(tasks);
            return ui.showDoneResult(doneTask);
        } catch (IndexOutOfBoundsException e) {
            throw new LukeException("Please enter a valid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
