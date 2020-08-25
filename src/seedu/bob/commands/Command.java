package seedu.bob.commands;

import seedu.bob.data.task.Tasklist;
import seedu.bob.exceptions.BobInvalidDateAndTimeException;
import seedu.bob.exceptions.BobInvalidNumberException;
import seedu.bob.exceptions.BobListIndexOutOfBoundsException;
import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;

import java.io.IOException;

/**
 * Represents command recognisable by Bob.
 */
public abstract class Command {

     public abstract boolean isExited();

     public abstract void execute(Tasklist tasks, Ui ui, Storage storage) throws IOException,
             BobInvalidDateAndTimeException, BobInvalidNumberException, BobListIndexOutOfBoundsException;
}
