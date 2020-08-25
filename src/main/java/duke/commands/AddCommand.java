package duke.commands;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {

    public Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(task);
        ui.formatAddTask(tasks.lst, task);
        storage.saveTaskList(tasks.lst);
    }
}
