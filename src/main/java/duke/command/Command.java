package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.ui.Response;
import duke.exceptions.DukeException;

/**
 * Represents a command entered by the user
 */
public abstract class Command {

    /**
     * Executes the appropriate action for the Command and return a Response containing data the GUI requires.
     *
     * @param tasks TaskList containing all tasks
     * @param ui Ui for formatting of message Strings to be displayed to user
     * @param storage Storage to retrieve and store Tasks entered by user
     * @throws DukeException if there is a problem when executing the action due to invalid user input
     * @return Response object containing data for the GUI to use
     */
    public abstract Response execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the Command causes the app to exit.
     *
     * @return true if Command causes the app to exit, false otherwise
     */
    public boolean isExit() {
        return false;
    }

}
