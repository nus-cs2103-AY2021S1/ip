package viscount.command;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountIndexOutOfBoundsException;
import viscount.exception.ViscountIoException;
import viscount.task.Task;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command.
     *
     * @param taskList Task list where tasks are stored.
     * @param ui Ui to display response.
     * @param storage Storage to save changes to disk.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size
     * @throws ViscountIoException If exception occurs with writing to disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ViscountIndexOutOfBoundsException,
            ViscountIoException {
        Task removedTask = taskList.remove(taskIndex);
        storage.saveToDisk(taskList.getTasks());
        ui.showDelete(removedTask, taskList.getTasksSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
