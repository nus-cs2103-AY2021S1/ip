package Duke.Command;

import Duke.*;
import Duke.Exception.*;

import java.io.IOException;

/**
 * Command deals with all commands recognisable by Duke.
 */
public abstract class Command {
    public abstract boolean isExited();

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException, IOException;
}
