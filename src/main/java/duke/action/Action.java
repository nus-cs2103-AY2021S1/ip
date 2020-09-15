package duke.action;

import java.util.Optional;

import duke.exception.DukeException;
import duke.ui.Ui;

/**
 * Represents a follow up action that is required proceeding a Command.
 */
public abstract class Action {

    /**
     * Displays a message to instruct User on the follow up action.
     */
    public abstract void prompt(Ui ui);

    /**
     * Feeds User input to an Action and retrieves any further Actions required.
     * @param input User provided input.
     * @return      Optional containing a following Action if any, empty Optional if not.
     */
    public abstract Optional<Action> receiveInputAndGetNextAction(
            String input) throws DukeException;

    /**
     * Carries out the process specific to each action.
     */
    protected abstract void execute();
}
