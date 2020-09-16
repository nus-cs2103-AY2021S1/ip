package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.exception.DukeException;
import duke.ui.Ui;
import duke.ui.UiForGui;

/**
 * Encapsulates a command for the Duke program. These are commands that users can enter as input to interact with Duke.
 * There are several types of commands, namely: add, delete, done and find task commands, and exit, list and unknown
 * commands.
 */
public abstract class Command {

    /** Indicates if the command is telling the program to terminate */
    private boolean isExit = false;

    /**
     * Executes the command in the CLI version of Duke.
     *
     * @param tasks The list of tasks in the program.
     * @param ui The Ui object being used in the program.
     * @param storage The Storage object being used in the program.
     * @throws DukeException If the command cannot be executed properly (is an invalid or unknown command).
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Executes the command in the GUI version of Duke.
     *
     * @param tasks The list of tasks in the program.
     * @param uiForGui The UiForGui object being used in the program.
     * @param storage The Storage object being used in the program.
     * @return String to show to the user at the end of the execution of the command.
     * @throws DukeException If the command cannot be executed properly (is an invalid or unknown command).
     */
    public abstract String execute(TaskList tasks, UiForGui uiForGui, Storage storage) throws DukeException;

    /**
     * Indicates if the command is telling the program to terminate.
     *
     * @return true if the command is telling the program to terminate, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Sets boolean flag isExit to indicate if the command is telling the program to terminate.
     *
     * @param exitStatus Value that isExit will be set to.
     */
    public void setExit(boolean exitStatus) {
        isExit = exitStatus;
    }
}
