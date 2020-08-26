package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Represents the different Commands.
 */
public abstract class Command {
    /**
     * Returns true if and only if it is a command to exit the program.
     *
     * @return true or false
     */
    public abstract boolean isExit();

    /**
     * Executes the command and performs operations accordingly.
     *
     * @param taskList taskList that stores Task objects
     * @param ui Ui that handles input and output to User
     * @param storage storage that handles data storage
     * @throws IOException Ioexception
     * @throws DukeException DukeException
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException;
}
