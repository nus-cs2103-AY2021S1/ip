package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Storage.Storage;
import duke.Tasks.TaskList;
import duke.Ui.Ui;

/**
 * An abstract class for all Command types to inherit from.
 */
public abstract class Command {

    /** boolean to indicate if an exit command is triggered.
     * True if the command is triggered and false otherwise.
     */
    protected boolean isExit = false;

    /**
     * Executes the command to perform its appropriate action.
     * @param tasks Duke task list.
     * @param ui Ui object to print user messages.
     * @param storage Storage object to load and save tasks to data file.
     * @throws DukeException
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Checks if the exit command has been triggered.
     * @return Boolean of whether the exit command has been triggered.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
