package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public abstract class AddCommand extends Command {

    protected String taskDetails;

    protected AddCommand(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    protected void addTask(Task newTask, TaskList tasks, Ui ui, Storage storage) {
        tasks.add(newTask);
        ui.addTask(newTask, tasks.size());
        storage.update(tasks);
    }
}
