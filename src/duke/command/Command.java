package duke.command;

import duke.exception.DukeExecutionException;
import duke.storage.Storage;

public abstract class Command {

    public boolean isExit() {
        return false;
    }

    public abstract void execute(Storage storage) throws DukeExecutionException;

}
