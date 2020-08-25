package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.DukeException;

/**
 * Represents a general Command that is abstract.
 * Concrete Commands will inherit from this class and take advantage
 * of polymorphism at run time.
 */
public abstract class Command {
    public abstract void execute(TaskList list, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
