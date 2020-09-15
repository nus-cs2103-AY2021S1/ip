package viscount.command;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountIndexOutOfBoundsException;
import viscount.exception.ViscountSaveDataException;
import viscount.task.Task;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {
    private Integer taskIndex;

    /**
     * Instantiates a new delete command.
     *
     * @param taskIndex Index of task deleted.
     */
    public DeleteCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command and returns the response from Viscount.
     *
     * @param taskList Task list where tasks are stored.
     * @param ui Ui to display response.
     * @param storage Storage to save changes to disk.
     * @return The response from Viscount.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size
     * @throws ViscountSaveDataException If exception occurs with writing to disk.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage)
            throws ViscountIndexOutOfBoundsException, ViscountSaveDataException {
        Task removedTask = taskList.remove(taskIndex);
        storage.saveToDisk(taskList.getTasks());
        return ui.getDeleteResponse(removedTask, taskList.getTasksSize());
    }

    /**
     * Compares this command with another object for equality.
     *
     * @param o Object compared.
     * @return True if this command is equal to the object, and false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        DeleteCommand deleteCommand = (DeleteCommand) o;
        return this.taskIndex == deleteCommand.taskIndex;
    }
}
