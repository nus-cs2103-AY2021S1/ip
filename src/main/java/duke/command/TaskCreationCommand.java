package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

abstract class TaskCreationCommand extends Command {
    public void execute(Task task, Ui ui, TaskList tasks) {
        tasks.add(task);
        ui.showAddSuccessful(task, tasks);
    }
}
