package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newTask) {
        super();
        this.newTask = newTask;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) {
        tasks.add(this.newTask);
        storage.addTask(this.newTask);
        ui.taskAdded(newTask, tasks);
    }
}
