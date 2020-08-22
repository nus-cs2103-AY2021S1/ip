package duke.command;

import duke.*;
import duke.exception.*;

public abstract class Command {
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeLoadingErrorException;

    public boolean isRunning() {
        return true;
    }
}
