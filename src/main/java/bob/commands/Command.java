package bob.commands;

import java.io.IOException;

import bob.data.task.Tasklist;
import bob.exceptions.BobInvalidDateAndTimeException;
import bob.exceptions.BobInvalidNumberException;
import bob.exceptions.BobListIndexOutOfBoundsException;
import bob.storage.Storage;

/**
 * Represents command recognisable by Bob.
 */
public abstract class Command {
    public abstract boolean isExited();

    public abstract String execute(Tasklist tasks, Storage storage)
             throws IOException, BobInvalidDateAndTimeException, BobInvalidNumberException,
             BobListIndexOutOfBoundsException;
}
