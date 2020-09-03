package alison.command;

import alison.exception.AlisonException;
import alison.task.Deadline;
import alison.task.Task;
import alison.tool.Storage;
import alison.tool.TaskList;
import alison.tool.Ui;

public class AddCommand extends Command {

    private final Task task;

    /**
     * Build a AddCommand with the Task being added.
     *
     * @param added the Task being added.
     */
    public AddCommand(Task added) {
        this.task = added;
    }

    /**
     * When executing a AddCommand, first try parse the time of the task if it is an instance of deadline,
     * then add the task to the provided TaskList,
     * and update the change in the hard disk.
     *
     * @param tasks TaskList.
     * @param ui User Interface.
     * @param storage Storage.
     * @return the add message of the UI
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlisonException {
        String error = "";
        if (task instanceof Deadline) {
            try {(
                    (Deadline) task).parseTime();
            } catch (AlisonException alisonException) {
                error += alisonException.getMessage() + "\n";
            }
        }
        tasks.add(task);
        storage.update(tasks);
        return error + ui.addTaskMsg(task, tasks);
    }

}

