package alison.command;

import alison.exception.AlisonException;
import alison.task.Deadline;
import alison.task.Task;
import alison.tool.Storage;
import alison.tool.TaskList;
import alison.tool.Ui;

public class AddCommand extends Command {

    private Task task;

    /**
     * Build a AddCommand with the Task being added.
     * @param added the Task being added.
     */
    public AddCommand(Task added) {
        this.task = added;
    }

    /**
     * When executing a AddCommand, first try parse the time of the task if it is an instance of deadline,
     * then add the task to the provided TaskList, print the add message of the UI,
     * and update the change in the hard disk.
     * @param tasks TaskList.
     * @param ui User Interface.
     * @param storage Storage.
     * @throws AlisonException
     */
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

