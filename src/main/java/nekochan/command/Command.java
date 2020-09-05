package nekochan.command;

import nekochan.exceptions.NekoException;
import nekochan.storage.Storage;
import nekochan.task.TaskList;

/**
 * The {@code Command} class provides a skeletal implementation of an executable command.
 */
public abstract class Command {

    protected boolean isCompleted = false;

    /**
     * Executes the command with the supplied {@code list} and {@code storage}.
     *
     * @param list    the currently loaded {@link TaskList} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    public abstract void execute(TaskList list, Storage storage);

    /**
     * Returns a {@link Response} after this {@code Command} has been executed.
     *
     * @throws NekoException if this {@code Command} has not been executed.
     */
    public abstract Response feedback() throws NekoException;
}
