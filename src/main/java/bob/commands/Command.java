package bob.commands;

import bob.data.task.Tasklist;

import bob.exceptions.BobInvalidDateAndTimeException;
import bob.exceptions.BobInvalidNumberException;
import bob.exceptions.BobListIndexOutOfBoundsException;
import java.io.IOException;

import bob.storage.Storage;
import bob.ui.Ui;


/**
 * Represents command recognisable by Bob.
 */
public abstract class Command {

     public abstract boolean isExited();

     public abstract void execute(Tasklist tasks, Ui ui, Storage storage) throws IOException,
             BobInvalidDateAndTimeException, BobInvalidNumberException, BobListIndexOutOfBoundsException;
}
