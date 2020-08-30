package seedu.duke.command;

import seedu.duke.DukeException;
import seedu.duke.Message;
import seedu.duke.Storage;
import seedu.duke.TaskList;

/**
 * Represents a command entered by the user for Duke to execute.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @param taskList a list of the user's tasks
     * @param storage  Deals with the saving of the user's tasks.
     * @throws DukeException if there is a problem when managing the taskList
     */
    Message execute(TaskList taskList, Storage storage) throws DukeException;

    /**
     * Determines if this <code>Command</code> causes Duke to stop running.
     *
     * @return whether or not this command terminates the program
     */
    boolean isDone();
}
