package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.task.Task;
import duke.Ui;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        if (index >= tasks.size() || index < 0) {
            throw new InvalidArgumentException("index");
        }
        Task deleted = tasks.remove(index);
        ui.print("Noted. I've removed this task:\n  " + deleted.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
