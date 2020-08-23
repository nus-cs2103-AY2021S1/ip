package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the parent Command class with abstract methods for execute and
 * checking completion status of program.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isDone();

}
