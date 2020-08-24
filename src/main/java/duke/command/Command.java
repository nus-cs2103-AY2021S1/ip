package duke.command;

import duke.*;
import duke.exception.*;

import java.io.IOException;

public abstract class Command {

    protected String command;
    protected String extra;
    protected boolean isExit;

    protected static final int PARSE_INDEX = 1;
    protected static final int TASK_INDEX = 1;

    public Command(String command) {
        this.command = command;
        extra = "";
        isExit = false;
    }

    public Command(String command, String extra) {
        this.command = command;
        this.extra = extra;
        isExit = false;
    }

    public Command(String command, boolean isExit) {
        this.command = command;
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(Storage storage, TaskList tasks, Ui ui)
            throws IOException, DukeEmptyArgumentException,
            DukeInvalidCommandException, DukeInvalidDateException,
            DukeInvalidArgumentException, DukeInvalidTaskException;
}
