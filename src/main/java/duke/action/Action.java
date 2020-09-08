package duke.action;

import duke.exception.DukeException;
import duke.ui.Ui;

import java.util.Optional;

public abstract class Action {

    public abstract void prompt(Ui ui);

    public abstract Optional<Action> receiveInputAndGetNextAction(String input) throws DukeException;

    protected abstract void execute();
}
