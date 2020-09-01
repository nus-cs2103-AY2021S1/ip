package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Command deals with all commands recognisable by Duke.
 */
public abstract class Command {

    public abstract boolean isExited();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;
}
