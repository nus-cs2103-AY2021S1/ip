package rogue.logic.directives;

import rogue.model.task.TaskList;

import rogue.logic.Report;

import rogue.storage.Storage;

import rogue.ui.Ui;

/**
 * Starts the exit sequence to stop {@code Rogue} from running.
 */
public class ExitDirective implements Executable {
    /** Message to add to a {@code Report} for when {@code Rogue} is exiting. */
    private final String MESSAGE_EXIT_SUCCESS = "hOpE To sEe yOu aGaIn. NoT.";

    /**
     * Starts the exit sequence for {@code Rogue}.
     *
     * @param storage   An instance of {@code Storage}.
     * @param tasks     An instance of {@code TaskList}.
     * @param ui        An instance of {@code Ui}.
     * @return A {@code Report} with a success message
     */
    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui) {
        return new Report(MESSAGE_EXIT_SUCCESS, true);
    }
}
