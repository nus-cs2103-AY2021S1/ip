package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DoneCommand extends Command {
    int taskId;

    public DoneCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.getTask(taskId);
        task.markAsDone();

        storage.updateExistingTask(taskId, task);

        ui.print(String.format("Nice! I've marked this task as done:\n%s", task));
    }
}
