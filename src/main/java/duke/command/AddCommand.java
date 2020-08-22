package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class AddCommand extends Command {
    Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(task);

        storage.saveNewTask(task);

        ui.print(String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", task,
                tasks.size()));
    }
}
