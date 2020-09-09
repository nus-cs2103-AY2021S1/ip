package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents command object that is based on user command.
 */
public abstract class Command {

    protected boolean isExit;

    /**
     * Executes command based on user input.
     * @param taskList tasks of user.
     * @param ui user interface object.
     * @param storage Storage object to retrieve and store data from file.
     * @throws DukeException If invalid input.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks boolean value of exit variable.
     * @return boolean value of exit variable.
     */
    public boolean isExit() {
        return isExit;
    }

}
