package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Command is an abstract class for all commands that the user will be inputting.
 * a <code>Command</code> object corresponds to the input of the user
 */
public abstract class Command {

    /**
     * Abstract method that executes the command.
     *
     * @param taskList The TaskList Object that handles the task operations
     * @param ui The UserInterface object that handles the interaction with users
     * @param storage The Storage Object that handles reading and writing from the datafile
     * @throws DukeException Exception that happens when there is an invalid input
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
