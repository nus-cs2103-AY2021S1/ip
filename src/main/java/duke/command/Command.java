package duke.command;

import duke.error.DukeError;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.UI;

/**
 * The base command class for other command class to inherit from.
 */

public interface Command {
    /**
     * Executes the command, taking in TaskList, UI and Storage to display the result in
     * UI while receiving data from TaskList and Storage and maybe modifying them concurrently.
     *
     * @param taskList The class that holds the list of Tasks
     * @param ui       The User Interface which interacts with the user
     * @param storage  The class that deals with saving and loading data of the user
     * @throws DukeError DukeErrors may be thrown in the process if user input is faulty
     */
    void execute(TaskList taskList, UI ui, Storage storage) throws DukeError;

    /**
     * Checks whether the command that ran exited the Duke program.
     *
     * @return Returns true if the command wants to exit the Duke program and vice versa
     */
    boolean isExit();
}
