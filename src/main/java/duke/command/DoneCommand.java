package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DoneCommand extends Command {
    private int taskNo;

    public DoneCommand(int taskNo) {
        this.taskNo = taskNo;
        isExit = false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.markDone(taskNo - 1);
        storage.saveTasks(taskList.getList());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof DoneCommand) {
            DoneCommand t = (DoneCommand) o;
            return t.taskNo == this.taskNo;
        } else {
            return false;
        }
    }
}
