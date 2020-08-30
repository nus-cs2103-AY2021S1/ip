package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeUnknownInputException;

/**
 * Represents an action dealing with unknown inputs.
 */
public class UnknownCommand implements Command {
    /**
     * Performs the printing of an unknown input error message.
     *
     * @param tasks The TaskList to add the task to.
     * @param ui The Ui to show responses or error messages.
     * @param storage The Storage to save the TaskList.
     * @return True because Duke should continue running.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(new DukeUnknownInputException());
        return true;
    }
}
