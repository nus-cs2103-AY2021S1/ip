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

    /**
     * Executes the DONE command, which marks an existing task in the taskList as DONE based on
     * index. Indexing of tasks in the taskList starts from 1.
     *
     * @param taskList
     * @param ui
     * @param storage
     * @throws DukeException
     */

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markTaskAsDone(this.idx);
        storage.save(taskList);
        ui.display("TASK IZ NAO DUNZ!!!!1!11!\n" + "  " + taskList.getTaskByIdx(this.idx));
    }

    @Override
    public String toString() {
        return this.cmd.toString() + ": " + this.idx;
    }
}

