package rogue.logic.directives;

import rogue.model.task.Task;
import rogue.model.task.TaskList;

import rogue.logic.Report;

import rogue.storage.Storage;
import rogue.storage.exceptions.StorageException;

import rogue.ui.Ui;

import rogue.commons.exceptions.IncorrectArgumentException;

/**
 * Deletes a {@code Task} from the {@code TaskList}.
 */
public class DeleteDirective implements Executable {
    /** Message to add to a {@code Report} when index is invalid. */
    private final String ERROR_INCORRECT_INDEX = "sToP TrYiNg tO FoOl mE. taSK #%d dOeS NoT ExIsT.";

    /** Message to add to a {@code Report} upon deleting a {@code Task} successfully. */
    private final String MESSAGE_DELETE_SUCCESS = "gReAt! OnE FeWeR ThInG To rEmEmBeR: %s\n" +
            "i sTiLl nEeD To rEmEmBeR %d tAsK(s).";

    private final int index;

    /**
     * Constructs a {@code DeleteDirective}.
     *
     * @param index The index of the {@code Task} to delete.
     */
    public DeleteDirective(int index) {
        this.index = index;
    }

    /**
     * Deletes a {@code Task} from the {@code TaskList}.
     *
     * All {@code Task} are then saved to {@code Storage}.
     *
     * @param storage   An instance of {@code Storage}.
     * @param tasks     The {@code TaskList} from which a {@code Task} is deleted.
     * @param ui        An instance of {@code Ui}.
     * @return A {@code Report} with a success message
     * @throws IncorrectArgumentException if the index provided is invalid.
     * @throws StorageException if data cannot be saved to file.
     */
    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui)
            throws IncorrectArgumentException, StorageException {
        try {
            Task deletedTask = tasks.delete(index);

            storage.save(tasks.getTaskList());

            return new Report(String.format(MESSAGE_DELETE_SUCCESS, deletedTask.toString(), tasks.count()));
        } catch (IndexOutOfBoundsException e) {
            throw new IncorrectArgumentException(String.format(ERROR_INCORRECT_INDEX, index + 1));
        }
    }
}
