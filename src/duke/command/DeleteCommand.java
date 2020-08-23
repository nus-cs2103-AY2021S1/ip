package duke.command;

import duke.exception.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;
import duke.exception.InvalidTaskNumberException;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            Task task = tasks.deleteTask(taskNumber - 1);
            storage.deleteTaskInFile(taskNumber);
            String message = ui.deleteSuccess(task, tasks.size());
            ui.sendMessage(message);
        }
    }
}
