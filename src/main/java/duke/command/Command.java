package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * Represents a command entered by the user
 */
public abstract class Command {

    /**
     * Execute the appropriate action for the Command.
     *
     * @param tasks task list containing all tasks
     * @param ui ui for interaction with user
     * @param storage storage to retrieve and store tasks entered by user
     * @throws DukeException if there is a problem when executing the action due to invalid user input
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Whether the Command causes the app to exit.
     *
     * @return true if Command causes the app to exit, false otherwise
     */
    public boolean isExit() {
        return false;
    }

}
