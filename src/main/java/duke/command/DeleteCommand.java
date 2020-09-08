package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int idx;
    public DeleteCommand(int idx) {
        super();
        this.cmd = CMD.DONE;
        this.idx = idx;
    }

    @Override
    public String getResponse(TaskList taskList, Storage storage) throws DukeException {
        String msg = "TASK IZ NAO DELETZ!!!!1!11!\n" + "  "
                + taskList.popTask(this.idx)
                + "\nNAO U HAS " + taskList.getNumberOfTasks() + " FINGS IN DA LIST LULZIES";
        storage.save(taskList);
        return msg;
    }

    @Override
    public String toString() {
        return this.cmd.toString() + ": " + this.idx;
    }
}
