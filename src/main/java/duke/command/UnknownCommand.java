package duke.command;

import duke.backend.Storage;
import duke.response.Response;
import duke.response.exception.DukeUnknownInputException;
import duke.task.TaskList;

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
    public String execute(TaskList tasks, Response ui, Storage storage) {
        return ui.showError(new DukeUnknownInputException());
    }
}
