package duke.command;

import duke.exception.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

/**
 * Class to represents all command. This class also contains specific string for commands.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Constructs a Command object with isExit as false.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Returns whether the Duke should stop running or not.
     * @return Duke's current state to continue running.
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Terminates Duke program.
     */
    public void exit() {
        this.isExit = true;
    }

    /**
     * Executes the command.
     * @param tasks TaskList of the current task.
     * @param ui Ui to deals with interactions with the user.
     * @param storage Storage to save the data to the hard disk.
     * @throws DukeException Thrown when something when wrong when executing the command.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
