package duke.action;

import java.util.Optional;

import duke.ui.Ui;

/**
 * Represents a pending Action that is waiting to be authorized by the User.
 */
public class ConfirmAction extends Action {

    /**
     * Expected User inputs.
     */
    private static final String YES = "y";
    private static final String NO = "n";

    /**
     * Pending Action to be executed.
     */
    private final Action actionToBeExecuted;

    /**
     * True if this ConfirmAction has been accessed multiple times.
     */
    private boolean hasBeenRepeated;

    /**
     * System Ui used by Duke.
     */
    private final Ui ui;

    /**
     * Constructs a new ConfirmAction.
     * @param actionToBeExecuted Action that is prepared
     *                           but not yet executed
     */
    public ConfirmAction(Ui ui,
                         Action actionToBeExecuted) {
        this.ui = ui;
        this.actionToBeExecuted = actionToBeExecuted;
        this.hasBeenRepeated = false;
    }

    /**
     * Prompts User confirmation. Emphasizes requirement of
     * either 'y' or 'n' input if User has entered otherwise.
     *
     * @param ui System Ui to be used.
     */
    @Override
    public void prompt(Ui ui) {
        if (hasBeenRepeated) {
            ui.showRepeatConfirmationMessage();
        } else {
            ui.showConfirmationMessage();
        }
    }

    /**
     * Constructs a new Deadline Task.
     * @param input Proposed changes that have been input by the User.
     * @return      An Optional containing this if User gives invalid input,
     * otherwise containing an empty Optional if successful, and a CancelledAction
     * if User chooses not to proceed.
     */
    @Override
    public Optional<Action> receiveInputAndGetNextAction(String input) {
        if (input.equals(YES)) {
            actionToBeExecuted.execute();
        } else if (input.equals(NO)) {
            ui.showCancelledActionMessage();
        } else {
            this.hasBeenRepeated = true;
            return Optional.of(this);
        }
        return Optional.empty();
    }

    /**
     * A ConfirmAction does not execute anyone.
     */
    @Override
    protected void execute() {
        // do nothing.
    }
}
