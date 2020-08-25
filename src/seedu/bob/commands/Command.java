package seedu.bob.commands;

import seedu.bob.data.task.Tasklist;

import seedu.bob.exceptions.BobInvalidDateAndTimeException;
import seedu.bob.exceptions.BobInvalidNumberException;
import seedu.bob.exceptions.BobListIndexOutOfBoundsException;
import java.io.IOException;

import seedu.bob.storage.Storage;
import seedu.bob.ui.Ui;


/**
 * Represents command recognisable by Bob.
 * @author Lim Zi Yang
 */
public abstract class Command {

     public abstract boolean isExited();

     public abstract void execute(Tasklist tasks, Ui ui, Storage storage) throws IOException,
             BobInvalidDateAndTimeException, BobInvalidNumberException, BobListIndexOutOfBoundsException;
}
