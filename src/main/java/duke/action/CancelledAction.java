package duke.action;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.Optional;

public class CancelledAction extends Action {
    @Override
    public void prompt(Ui ui) {
        ui.showCancelledActionMessage();
    }

    @Override
    public Optional<Action> receiveInputAndGetNextAction(String input) throws DukeException {
        return Optional.empty();
    }

    @Override
    protected void execute() {
        // nothing.
    }
}
