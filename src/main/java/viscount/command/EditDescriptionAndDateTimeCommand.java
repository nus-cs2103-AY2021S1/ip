package viscount.command;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountIndexOutOfBoundsException;
import viscount.exception.ViscountSaveDataException;
import viscount.exception.ViscountUnsupportedOperationException;

/**
 * Represents a combination of edit description and edit date time command.
 */
public class EditDescriptionAndDateTimeCommand extends EditCommand {
    private EditDescriptionCommand editDescriptionCommand;
    private EditDateTimeCommand editDateTimeCommand;

    /**
     * Instantiates a new edit description and date time command.
     *
     * @param taskIndex Index of task edited.
     * @param editDescriptionCommand Edit description command represented.
     * @param editDateTimeCommand Edit date time command represented.
     */
    public EditDescriptionAndDateTimeCommand(Integer taskIndex,
                                             EditDescriptionCommand editDescriptionCommand,
                                             EditDateTimeCommand editDateTimeCommand) {
        super(taskIndex);
        this.editDescriptionCommand = editDescriptionCommand;
        this.editDateTimeCommand = editDateTimeCommand;
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
        String response = "";
        response += editDateTimeCommand.executeAndGetResponse(taskList, ui, storage) + "\n";
        response += editDescriptionCommand.executeAndGetResponse(taskList, ui, storage);
        return response;
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

        EditDescriptionAndDateTimeCommand editDescriptionAndDateTimeCommand = (EditDescriptionAndDateTimeCommand) o;
        boolean hasSameDescriptionCommand =
                this.editDescriptionCommand.equals(editDescriptionAndDateTimeCommand.editDescriptionCommand);
        boolean hasSameDateTimeCommand =
                this.editDateTimeCommand.equals(editDescriptionAndDateTimeCommand.editDateTimeCommand);
        return hasSameDescriptionCommand && hasSameDateTimeCommand;
    }
}
