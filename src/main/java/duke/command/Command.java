package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Represents a command.
 */
public abstract class Command {

    /**
     * Executes an operation.
     *
     * @param tasks TaskList linked to the program.
     * @param ui Ui linked to the program.
     * @param storage Storage linked to the program.
     * @return String that contains the executed command.
     * @throws DukeException If an error is encountered.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return Boolean value.
     */
    public abstract boolean isExit();

}
