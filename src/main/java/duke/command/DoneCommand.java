package duke.command;

import duke.*;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    private int idx;
    public DoneCommand(int idx) {
        super();
        this.cmd = CMD.DONE;
        this.idx = idx;
    }

    @Override
    public String getResponse(TaskList tasklist, Storage storage) throws DukeException {
        tasklist.markTaskAsDone(this.idx);
        storage.save(tasklist);
        return "TASK IZ NAO DUNZ!!!!1!11!\n" + "  " + tasklist.getTaskByIdx(this.idx);
    }

    @Override
    public String toString() {
        return this.cmd.toString() + ": " + this.idx;
    }
}

