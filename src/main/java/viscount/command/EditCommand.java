package viscount.command;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountException;

/**
 * Represents an edit command.
 */
public abstract class EditCommand extends Command {
    protected Integer taskIndex;

    /**
     * Instantiates a new edit command.
     *
     * @param taskIndex Index of task edited.
     */
    public EditCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the edit command and returns the response from Viscount.
     *
     * @param taskList Task list where tasks are stored.
     * @param ui Ui to display response.
     * @param storage Storage to save changes to disk.
     * @return The response from Viscount.
     * @throws ViscountException If exception occurs during execution.
     */
    @Override
    public abstract String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws ViscountException;
}
