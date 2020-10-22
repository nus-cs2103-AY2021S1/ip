package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class UpdateCommand extends Command {
    private int taskIdx;
    private String newValue;
    private boolean isUpdateDate;

    public UpdateCommand(int idx, String newValue, boolean isUpdateDate) {
        super();
        this.cmd = CMD.UPDATE;
        this.taskIdx = idx;
        this.newValue = newValue;
        this.isUpdateDate = isUpdateDate;
    }

    @Override
    public String getResponse(TaskList tasklist, Storage storage) throws DukeException {
        String msg = "TASK IZ NAO UPDATEZ!!1!11!!\n" + " ";

        if (this.isUpdateDate) {
            msg += tasklist.updateTaskDate(this.taskIdx, this.newValue);
        } else {
            msg += tasklist.updateTaskDescription(this.taskIdx, this.newValue);
        }

        storage.save(tasklist);
        return msg;
    }

    @Override
    public String toString() {
        return this.cmd.toString() + ": " + this.taskIdx;
    }
}
