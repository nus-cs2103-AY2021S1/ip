package duke.command;

import duke.backend.Storage;
import duke.task.TaskList;
import duke.response.Response;
import duke.exception.DukeUnknownInputException;

/**
 * Represents an action dealing with unknown inputs.
 */
public class UnknownCommand implements Command {
    /**
     * Returns false because command does not exit.
     *
     * @return false.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }

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
