package viscount.command;

import java.time.LocalDateTime;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountIndexOutOfBoundsException;
import viscount.exception.ViscountSaveDataException;
import viscount.exception.ViscountUnsupportedOperationException;

/**
 * Represents an edit date time command.
 */
public class EditDateTimeCommand extends EditCommand {
    private LocalDateTime newDateTime;

    /**
     * Instantiates a new edit date time command.
     *
     * @param taskIndex Index of task edited.
     * @param newDateTime New date time of task edited.
     */
    public EditDateTimeCommand(Integer taskIndex, LocalDateTime newDateTime) {
        super(taskIndex);
        this.newDateTime = newDateTime;
    }

    /**
     * Executes the edit date time command and returns the response from Viscount.
     *
     * @param taskList Task list where tasks are stored.
     * @param ui Ui to display response.
     * @param storage Storage to save changes to disk.
     * @return The response from Viscount.
     * @throws ViscountIndexOutOfBoundsException If taskIndex is < 0 or >= list size.
     * @throws ViscountSaveDataException If exception occurs with writing to disk.
     * @throws ViscountUnsupportedOperationException If editing todo date time.
     */
    @Override
    public String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage)
            throws ViscountIndexOutOfBoundsException, ViscountSaveDataException, ViscountUnsupportedOperationException {
        taskList.editTaskDateTime(taskIndex, newDateTime);
        storage.saveToDisk(taskList.getTasks());
        return ui.getEditDateTimeResponse(taskList.getTask(taskIndex));
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

        EditDateTimeCommand editDateTimeCommand = (EditDateTimeCommand) o;
        boolean hasSameTaskIndex = this.taskIndex.equals(editDateTimeCommand.taskIndex);
        boolean hasSameDateTime = (this.newDateTime == null)
                ? (editDateTimeCommand.newDateTime == null)
                : (editDateTimeCommand.newDateTime != null)
                        && this.newDateTime.isEqual(editDateTimeCommand.newDateTime);
        return hasSameTaskIndex && hasSameDateTime;
    }
}
