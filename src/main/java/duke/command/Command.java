package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a general action to be completed.
 */
public abstract class Command {

    /** Boolean value to indicate if the Command should exit the programme */
    private boolean isExit;

    /**
     * Constructs a <code>Command</code> object.
     *
     * @param isExit Indicates if the command should exit the programme.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Represents a general method that carries out the action of the Command.
     *
     * @param tasks TaskList to store Task.
     * @param ui Ui to interact with users.
     * @param storage Storage use by Duke to save and load files.
     * @return CommandResponse A response to the user.
     * @throws DukeException If error specific to Duke occurs.
     */
    public abstract CommandResponse execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns the Command's isExit value.
     *
     * @return isExit value.
     */
    public boolean getIsExit() {
        return isExit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Command) {
            Command c = (Command) obj;
            return c.isExit == this.isExit;
        } else {
            return false;
        }
    }
}
