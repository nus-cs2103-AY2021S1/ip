package main.java.duke.command;

import main.java.duke.exception.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

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
     * @throws DukeException If an error is encountered.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns a boolean that dictates if the program is running.
     *
     * @return Boolean value.
     */
    public abstract boolean isExit();

}
