package duke.Command;

import duke.Exception.DukeException;
import duke.Task.TaskList;

import duke.Ui.Ui;

import duke.Storage;

/**
 * Represents an executable command.
 */
public abstract class Command {

    protected boolean isExit = false;

    /**
     * Executes the command.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the command triggers termination of the program.
     *
     * @return True if the command triggers termination of the program.
     */
    public boolean isExit() {
        return isExit;
    }
}