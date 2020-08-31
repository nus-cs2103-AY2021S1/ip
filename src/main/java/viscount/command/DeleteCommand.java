package viscount.command;

import viscount.*;

import viscount.exception.ViscountSaveDataException;
import viscount.exception.ViscountIndexOutOfBoundsException;

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
     * @throws ViscountSaveDataException If exception occurs with writing to disk.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws ViscountIndexOutOfBoundsException,
            ViscountSaveDataException {
        Task removedTask = taskList.remove(taskIndex);
        storage.saveToDisk(taskList.getTasks());
        return ui.getDeleteResponse(removedTask, taskList.getTasksSize());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
