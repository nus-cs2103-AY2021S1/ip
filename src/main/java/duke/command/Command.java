package duke.command;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The {@code Command} class provides a skeletal implementation of an executable command.
 */
public abstract class Command {

    protected boolean completed = false;

    /**
     * Executes the command with the supplied {@code list} and {@code storage}.
     *
     * @param list    the currently loaded {@link TaskList} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    public abstract void execute(TaskList list, Storage storage);

    /**
     * Prints a feedback after this {@code Command} has been executed.
     *
     * @throws DukeException if this {@code Command} has not been executed.
     */
    public abstract String feedback() throws DukeException;

    /**
     * Returns true if this {@code Command} should terminate the program.
     *
     * @return true if this {@code Command} should terminate the program.
     */
    public abstract boolean isExit();
}
