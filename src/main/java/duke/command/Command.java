package duke.command;

import duke.exception.*;
import duke.storage.*;
import duke.tasklist.*;
import duke.ui.*;

public abstract class Command {
    boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public abstract boolean getIsExit();

    @Override
    public String toString() {
        return "Duke Command";
    }
}
