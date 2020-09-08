package nekochan.command;

import nekochan.exceptions.NekoException;
import nekochan.model.NekoHistory;
import nekochan.storage.Storage;

/**
 * The {@code Command} class provides a skeletal implementation of an executable command.
 */
public abstract class Command {

    protected boolean isCompleted = false;

    /**
     * Executes the command with the supplied {@code list} and {@code storage}.
     * @param history the currently loaded {@link NekoHistory} object.
     * @param storage the currently loaded {@link Storage} object.
     */
    public abstract void execute(NekoHistory history, Storage storage);

    /**
     * Returns a {@link Response} after this {@code Command} has been executed.
     *
     * @return a {@code Response} object containing the result of executing this {@code Command}.
     * @throws NekoException if this {@code Command} has not been executed.
     */
    public abstract Response feedback() throws NekoException;
}
