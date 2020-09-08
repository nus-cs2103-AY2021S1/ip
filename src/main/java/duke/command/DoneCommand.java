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
    public String getResponse(TaskList taskList, Storage storage) throws DukeException {
        taskList.markTaskAsDone(this.idx);
        storage.save(taskList);
        return "TASK IZ NAO DUNZ!!!!1!11!\n" + "  " + taskList.getTaskByIdx(this.idx);
    }

    @Override
    public String toString() {
        return this.cmd.toString() + ": " + this.idx;
    }
}

