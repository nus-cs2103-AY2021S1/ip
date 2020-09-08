package duke.action;

import duke.ui.Ui;

import java.util.Optional;

public class ConfirmAction extends Action {

    private final Action actionToBeExecuted;
    private boolean hasBeenRepeated;

    private static final String YES = "y";
    private static final String NO = "n";

    public ConfirmAction(Action actionToBeExecuted) {
        this.actionToBeExecuted = actionToBeExecuted;
        this.hasBeenRepeated = false;
    }

    @Override
    public void prompt(Ui ui) {
        if (hasBeenRepeated) {
            ui.showRepeatConfirmationMessage();
        } else {
            ui.showConfirmationMessage();
        }
    }

    @Override
    public Optional<Action> receiveInputAndGetNextAction(String input) {
        if (input.equals(YES)) {
            actionToBeExecuted.execute();
        } else if (input.equals(NO)) {
            return Optional.of(new CancelledAction());
        } else {
            this.hasBeenRepeated = true;
            return Optional.of(this);
        }
        return Optional.empty();
    }

    @Override
    protected void execute() {
        // do nothing.
    }
}
