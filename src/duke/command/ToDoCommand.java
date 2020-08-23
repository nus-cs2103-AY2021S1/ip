package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.ToDoTask;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class ToDoCommand extends Command {
    private String taskName;

    public ToDoCommand(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = new ToDoTask(taskName);
        tasks.addTask(task);
        storage.saveTaskToFile(task);
        String message = ui.addSuccess(task, tasks.size());
        ui.sendMessage(message);
    }
}
