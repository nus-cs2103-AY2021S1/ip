package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Represents a Command given by the user.
 */
public abstract class Command {
    /**
     * Executes the actions stipulated by the command given by the user.
     * @param list A TaskList containing the user's Tasks.
     * @param storage A Storage object that handles the storage of tasks in local storage, allowing them to persist.
     * @return A String that contains a user message detailing the success of the command.
     * @throws DukeException If any specific exceptions occur when executed.
     */
    public abstract String execute(TaskList list, Storage storage) throws DukeException;

    /**
     * Gets whether the command represents an ExitCommand.
     * This will be overridden in the ExitCommand subclass.
     * This is the default implementation for all other subclasses.
     * @return false (default implementation).
     */
    public boolean shouldExit() {
        return false;
    }
}
