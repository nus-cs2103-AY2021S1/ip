package duke.command;

import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidTaskNumberException;
import duke.task.Task;

public class DoneCommand extends Command {
    private int taskNumber;

    public DoneCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new InvalidTaskNumberException();
        } else {
            Task task = tasks.getTask(taskNumber - 1);
            task.setStatusToDone();
            storage.changeTaskInFile(taskNumber);
            String message = ui.doneSuccess(task);
            ui.sendMessage(message);
        }
    }
}
