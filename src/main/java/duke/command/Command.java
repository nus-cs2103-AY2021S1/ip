package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Abstract class that models a command.
 */
public abstract class Command {
    private boolean shouldLoop;

    /**
     * Constructs a Command.
     *
     * @param shouldLoop Boolean representing whether the command should continue looping or not.
     */
    public Command(boolean shouldLoop) {
        this.shouldLoop = shouldLoop;
    }

    /**
     * Executes the command and then returns a response.
     *
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     * @return String Message when completing the command.
     * @throws DukeException If could not execute command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage)
            throws DukeException;

    /**
     * Checks whether the command should continue the loop or not.
     *
     * @return Boolean representing whether to continue looping or not.
     */
    public boolean shouldLoop() {
        return this.shouldLoop;
    }
}
