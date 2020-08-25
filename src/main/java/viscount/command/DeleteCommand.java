package viscount.command;

import viscount.*;
import viscount.exception.ViscountException;
import viscount.exception.ViscountIOException;
import viscount.exception.ViscountIndexOutOfBoundsException;
import viscount.task.Task;

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
     * @throws ViscountIOException If exception occurs with writing to disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws ViscountIndexOutOfBoundsException,
            ViscountIOException {
        Task removedTask = taskList.remove(taskIndex);
        storage.saveToDisk(taskList.getTasks());
        ui.showDelete(removedTask, taskList.getTasksSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
