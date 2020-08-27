package alison.command;

import alison.exception.AlisonException;
import alison.task.Deadline;
import alison.task.Task;
import alison.tool.Storage;
import alison.tool.TaskList;
import alison.tool.Ui;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task added) {
        this.task = added;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlisonException {
        if (task instanceof Deadline) {
            try {
                ((Deadline) task).parseTime();
            } catch (AlisonException alisonException) {
                System.out.println(alisonException.getMessage());
            }
        }
        tasks.add(task);
        ui.addTaskMsg(task, tasks);
        storage.update(tasks);
    }
}
