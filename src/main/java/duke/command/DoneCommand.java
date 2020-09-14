package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

public class DoneCommand extends Command {
    int taskNumberToMark;

    public DoneCommand(int taskNumberToMark) {
        this.taskNumberToMark = taskNumberToMark;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert taskNumberToMark > 0 : "Task number must be greater than 1";
        if (taskNumberToMark > tasks.getSize() | taskNumberToMark < 1) {
            throw new DukeException("There is no such task number.");
        } else {
            Task taskToMark = tasks.markTask(this.taskNumberToMark);
            String finalMessage = "Nicely done. I've marked this task as done:" + "\n" + taskToMark;
            ui.showMessage(finalMessage);
            storage.save(tasks);
            return finalMessage;
        }
    }
}