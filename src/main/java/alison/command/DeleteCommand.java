package alison.command;

import alison.exception.AlisonException;
import alison.task.Task;
import alison.tool.Storage;
import alison.tool.TaskList;
import alison.tool.Ui;

public class DeleteCommand extends Command {

    private int index;

    /**
     * Build a DeleteCommand with the index of the task being deleted.
     * @param i the index of the task being deleted.
     */
    public DeleteCommand(int i) {
        index = i;
    }

    /**
     * When executing a DeleteCommand, first try to delete the task with the index provided,
     * print the message and update the local hard disk accordingly.
     * @param tasks TaskList.
     * @param ui User Interface.
     * @param storage Storage.
     * @throws AlisonException if fails to find the task with the given index.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlisonException {
        try {
            Task removedTask = tasks.remove(index - 1);
            ui.removeTaskMsg(removedTask, tasks);
            storage.update(tasks);
        } catch (Exception e) {
            throw AlisonException.invalidIndexException();
        }
    }

}
