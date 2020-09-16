package rogue.logic.directives;

import rogue.model.report.Report;
import rogue.logic.directives.exceptions.ExecutionException;
import rogue.model.task.Task;
import rogue.model.task.TaskList;
import rogue.storage.Storage;
import rogue.storage.exceptions.StorageException;
import rogue.ui.Ui;

/**
 * Deletes a {@code Task} from the {@code TaskList}.
 */
public class DeleteDirective implements Executable {
    /** Message for when index is invalid. */
    private static final String ERROR_INCORRECT_INDEX = "Stop trying to fool me. Task #%d does not exist.";

    /** Message upon deleting a {@code Task} successfully. */
    private static final String MESSAGE_DELETE_SUCCESS = "Great! One fewer thing to remember: %s\n"
            + "I still need to remember %d task(s).";

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
     * @throws ExecutionException if the index does not match any {@code Task} in the {@code TaskList}.
     * @throws StorageException if data cannot be saved to file.
     */
    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui)
            throws ExecutionException, StorageException {
        try {
            Task deletedTask = tasks.delete(index);

            assert deletedTask != null : "Deleted task cannot be null!";

            storage.save(tasks.getTaskList());

            return new Report(String.format(MESSAGE_DELETE_SUCCESS, deletedTask.toString(), tasks.count()));
        } catch (IndexOutOfBoundsException e) {
            throw new ExecutionException(String.format(ERROR_INCORRECT_INDEX, index + 1));
        }
    }
}
