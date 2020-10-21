package duke.command;

import duke.Ui;
import duke.Storage;
import duke.DukeException;

/**
 * An abstract class forming the skeleton of more specific commands.
 */
public abstract class Command {

    /**
     * Executes the command, propagating changes to the task-list, user interface and storage
     * where applicable.
     *
     * @param ui The user interface.
     * @param storage The storage.
     * @throws DukeException If thrown by any of the processes involved in the execution of the command.
     */
    public abstract void execute(Ui ui, Storage storage) throws DukeException;

    /** Indicates if the command should prompt the application to exit.
     *
     * @return true, if the command is an ExitCommand; false otherwise.
     */
    public abstract boolean isExit();
}
