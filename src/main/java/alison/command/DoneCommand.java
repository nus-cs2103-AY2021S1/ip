package alison.command;

import alison.exception.AlisonException;
import alison.task.Task;
import alison.tool.Storage;
import alison.tool.TaskList;
import alison.tool.Ui;

public class DoneCommand extends Command {

    private final int index;

    /**
     * Build a DoneCommand with the index of the task being marked as Done.
     *
     * @param i the index of the task being marked as Done.
     */
    public DoneCommand(int i) {
        index = i;
    }

    /**
     * To execute a DoneCommand,
     * first, try to find the task with the given index,
     * mark the task as done, and update local hard disk accordingly.
     *
     * @param tasks TaskList.
     * @param ui User Interface.
     * @param storage Storage.
     * @return the response.
     * @throws AlisonException when Command failed to be executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlisonException {
        try {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            storage.update(tasks);
            return ui.markDoneMsg(task);
        } catch (Exception e) {
            throw AlisonException.invalidIndexException();
        }
    }

}
