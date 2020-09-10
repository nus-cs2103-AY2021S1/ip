package duke.logic.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that can be executed.
 */
public abstract class Command {
    /**
     * Execute the command that user calls.
     *
     * @param tasks duke.tasks.Task list of all tasks.
     * @param ui duke.ui.Ui to deal with interaction with user.
     * @param storage duke.storage.Storage to load and save tasks.
     * @return String message to show the executed event.
     * @throws DukeException If unable to update file to storage's filepath when required.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean isExit();
}
