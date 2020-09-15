package viscount.command;

import viscount.Storage;
import viscount.TaskList;
import viscount.Ui;
import viscount.exception.ViscountException;

/**
 * Represents a command.
 */
public abstract class Command {
    /**
     * Executes the command and returns the response from Viscount.
     *
     * @param taskList Task list where tasks are stored.
     * @param ui Ui to display response.
     * @param storage Storage to save changes to disk.
     * @return The response from Viscount.
     * @throws ViscountException If exception occurs during execution.
     */
    public abstract String executeAndGetResponse(TaskList taskList, Ui ui, Storage storage) throws ViscountException;
}
