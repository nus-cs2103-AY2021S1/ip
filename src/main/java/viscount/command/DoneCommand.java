package viscount.command;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountIndexOutOfBoundsException;
import viscount.exception.ViscountSaveDataException;

/**
 * Represents a done command.
 */
public class DoneCommand extends Command {
    private Integer taskIndex;

    /**
     * Instantiates a new done command.
     *
     * @param taskIndex Index of task marked as done.
     */
    public DoneCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the done command and returns the response from Viscount.
     *
     * @param taskList Task list where tasks are stored.
     * @param ui Ui to display response.
     * @param storage Storage to save changes to disk.
     * @return The response from Viscount.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size.
     * @throws ViscountSaveDataException If exception occurs with writing to disk.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage)
            throws ViscountIndexOutOfBoundsException, ViscountSaveDataException {
        taskList.markDone(taskIndex);
        storage.saveToDisk(taskList.getTasks());
        return ui.getDoneResponse(taskList.getTask(taskIndex));
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

        DoneCommand doneCommand = (DoneCommand) o;
        return this.taskIndex == doneCommand.taskIndex;
    }
}
