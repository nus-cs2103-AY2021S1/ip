package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    int taskId;

    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = tasks.deleteTask(taskId);

        storage.deleteExistingTask(taskId);

        ui.print(String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.", task,
                tasks.size()));
    }
}
