package duckie.command;

import duckie.task.Task;
import duckie.task.TaskList;
import duckie.exception.DuckieException;
import duckie.Ui;
import duckie.Storage;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        tasks.addTask(this.task);
        try {
            storage.saveToFile(tasks.getTaskList());
        } catch (DuckieException e) {
            throw e;
        }
        Ui.addTaskReply(this.task, tasks.getTaskList());
    }
}
