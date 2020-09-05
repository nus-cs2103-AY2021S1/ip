package fei.command;

import fei.exception.FeiException;
import fei.task.Task;
import fei.tool.Storage;
import fei.tool.TaskList;
import fei.tool.Ui;

public class DeleteCommand extends Command {

    private final int index;

    /**
     * Build a DeleteCommand with the index of the task being deleted.
     *
     * @param i the index of the task being deleted.
     */
    public DeleteCommand(int i) {
        index = i;
    }

    /**
     * When executing a DeleteCommand, first try to delete the task with the index provided,
     * print the message and update the local hard disk accordingly.
     *
     * @param tasks TaskList.
     * @param ui User Interface.
     * @param storage Storage.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removedTask = tasks.remove(index - 1);
            storage.update(tasks);
            return ui.removeTaskMsg(removedTask, tasks);
        } catch (Exception e) {
            return FeiException.invalidIndexException().getMessage();
        }
    }

}
