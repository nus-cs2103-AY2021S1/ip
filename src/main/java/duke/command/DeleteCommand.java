package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private String[] nextCommandArr;

    public DeleteCommand(String[] nextCommandArr) {
        this.nextCommandArr = nextCommandArr;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int deleteTaskRef = Integer.parseInt(this.nextCommandArr[1]);
            Task deleteTask = taskList.get(deleteTaskRef - 1);
            taskList.remove(deleteTaskRef - 1);
            ui.deleteTaskText(deleteTask, taskList);
        } catch (Exception e) {
            throw new DukeException("Please provide a valid task number to delete~");
        }
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
