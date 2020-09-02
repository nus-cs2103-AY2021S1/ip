package main.java.duke.command;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

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
     * @throws DukeException If unable to either carry out the specific actions.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if program stops running upon command.
     *
     * @return False by default.
     */
    public boolean isExit() {
        return false;
    }

}
