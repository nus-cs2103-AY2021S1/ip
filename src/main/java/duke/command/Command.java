package duke.command;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.storage.DukeState;
import duke.storage.DukeStateManager;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;

/**
 * Represents a command entered by the user
 */
public abstract class Command {

    /**
     * Executes the appropriate action for the Command and return a Response containing data the GUI requires to
     * display feedback to the user.
     *
     * @param tasks TaskList containing all tasks
     * @param ui Ui for formatting of message Strings to be displayed to user
     * @param storage Storage to retrieve and store Tasks entered by user
     * @param dukeStateManager DukeStateManager to manage the current state of Duke
     * @return Response object containing data for the GUI to use
     * @throws DukeException if there is a problem when executing the action due to invalid user input
     * @throws IOException if there is an error with storing changes into storage file
     */
    public abstract Response execute(TaskList tasks, Ui ui, Storage storage, DukeStateManager dukeStateManager)
            throws DukeException, IOException;

    /**
     * Returns whether the Command causes the app to exit.
     *
     * @return true if Command causes the app to exit, false otherwise
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Instructs the given DukeStateManager to add a new DukeState created with the given TaskList and Storage.
     *
     * @param dukeStateManager DukeStateManager managing the state and history of the Duke app
     * @param tasks TaskList object containing the tasks to be updated into the DukeState
     * @param storage Storage object to update the changes to state into storage file
     */
    protected void storeState(DukeStateManager dukeStateManager, TaskList tasks, Storage storage) {
        DukeState newState = new DukeState(tasks.getCopyOf(), storage);
        dukeStateManager.addHistory(newState);
    }

}
