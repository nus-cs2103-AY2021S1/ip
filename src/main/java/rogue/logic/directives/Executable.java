package rogue.logic.directives;

import rogue.storage.Storage;
import rogue.model.task.TaskList;
import rogue.ui.Ui;

import rogue.logic.Report;

import rogue.commons.exceptions.IncorrectArgumentException;
import rogue.storage.exceptions.StorageException;

/**
 * Represents an action that can be executed.
 */
public interface Executable {
    /**
     * Gets {@code Rogue} to execute an action.
     *
     * @param storage   An instance of {@code Storage}.
     * @param tasks     An instance of {@code TaskList}.
     * @param ui        An instance of {@code Ui}.
     * @return A {@code Report} containing a message
     * @throws IncorrectArgumentException when incorrect parameters are provided for an {@code Action}.
     * @throws StorageException if data cannot be saved to file.
     */
    Report execute(Storage storage, TaskList tasks, Ui ui) throws IncorrectArgumentException, StorageException;
}
