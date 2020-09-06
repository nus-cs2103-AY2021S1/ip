package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command which is an abstract class.
 */
public abstract class Command {

    /**
     * Carries out different actions corresponding to type of command.
     *
     * @param tasks Task list representing current tasks.
     * @param ui User interface interacting with user.
     * @param storage Storage Storage in charge of saving file to hard disk.
     * @return A string representing Duke's response after executing command.
     * @throws DukeException If unable to either carry out the specific actions.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if program stops running upon command.
     *
     * @return False by default.
     */
    public boolean isExit() {
        return false;
    }

}
