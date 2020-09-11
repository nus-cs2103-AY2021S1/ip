package viscount.command;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountIndexOutOfBoundsException;
import viscount.exception.ViscountSaveDataException;

/**
 * Represents an edit description command.
 */
public class EditDescriptionCommand extends EditCommand {
    private String newDescription;

    /**
     * Instantiates a new edit description command.
     *
     * @param taskIndex Index of task edited.
     * @param newDescription New description of task edited.
     */
    public EditDescriptionCommand(Integer taskIndex, String newDescription) {
        super(taskIndex);
        this.newDescription = newDescription;
    }

    /**
     * Executes the edit description command and returns the response from Viscount.
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
        taskList.editTaskDescription(taskIndex, newDescription);
        storage.saveToDisk(taskList.getTasks());
        return ui.getEditDescriptionResponse(taskList.getTask(taskIndex));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        EditDescriptionCommand editDescriptionCommand = (EditDescriptionCommand) o;
        boolean hasSameTaskIndex = this.taskIndex.equals(editDescriptionCommand.taskIndex);
        boolean hasSameNewDescription = this.newDescription.equals(editDescriptionCommand.newDescription);
        return hasSameTaskIndex && hasSameNewDescription;
    }
}
