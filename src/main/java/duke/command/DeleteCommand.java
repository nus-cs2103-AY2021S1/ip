package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;
import duke.DukeException;
import duke.task.Task;

/**
 * The DeleteCommand class contains methods pertaining to the DeleteCommand.
 */
public class DeleteCommand extends Command {

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            if (taskList.size() >= taskNumber && taskNumber > 0) {
                Task task = taskList.removeTask(taskNumber);
                storage.updateData("", taskNumber);
                ui.deleteTask(task, taskList);
            } else {
                throw new DukeException("Oops! No such task!");
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
