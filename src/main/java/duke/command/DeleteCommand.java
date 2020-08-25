package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    private int taskNo;

    public DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
        isExit = false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.deleteTask(taskNo - 1);
        storage.saveTasks(taskList.getList());
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof DeleteCommand) {
            DeleteCommand t = (DeleteCommand) o;
            return t.taskNo == this.taskNo;
        } else {
            return false;
        }
    }
}