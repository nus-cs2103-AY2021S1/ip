package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DeleteCommand extends Command {
    int taskNumberToDelete;

    public DeleteCommand(int taskNumberToDelete) {
        this.taskNumberToDelete = taskNumberToDelete;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert taskNumberToDelete > 0 : "Task number must be greater than 1";
        if (taskNumberToDelete > tasks.getSize() | taskNumberToDelete < 1) {
            throw new DukeException("There is no such task number.");
        } else {
            Task taskToDelete = tasks.deleteTask(this.taskNumberToDelete);
            String successfulMsg = "Noted. I've removed this task:\n" + taskToDelete;
            String remainingTasksMsg = "\nNow you have " + tasks.getSize() + " tasks in the list.";
            String finalMsg = successfulMsg + remainingTasksMsg;
            ui.showMessage(finalMsg);
            storage.save(tasks);
            return finalMsg;
        }
    }
}