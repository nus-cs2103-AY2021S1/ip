package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Ui;

import java.util.Objects;

public class AddTaskCommand implements Command {
    private Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) throws DukeException {
        tasks.addTask(task);
        ui.print("Got it. I've added this task:\n  " + task.toString() + "\nNow you have " + tasks.size()
                + " tasks in the list.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddTaskCommand that = (AddTaskCommand) o;
        return Objects.equals(task, that.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
