package duke.command;

import duke.*;
import duke.exception.*;

import java.io.IOException;

/**
 * Represents a command
 */
public abstract class Command {

    protected String command;
    protected String extra;
    protected boolean isExit;

    protected static final int PARSE_INDEX = 1;
    protected static final int TASK_INDEX = 1;

    /**
     * Class constructor without additional arguments.
     * @param command String parsed by Parser object.
     */
    public Command(String command) {
        this.command = command;
        extra = "";
        isExit = false;
    }

    /**
     * Class constructor with additional String argument.
     * @param command String parsed by Parser object.
     * @param extra String parsed by Parser object representing
     *              extra argument for command string.
     */
    public Command(String command, String extra) {
        this.command = command;
        this.extra = extra;
        isExit = false;
    }

    /**
     * Class constructor with addition boolean argument.
     * @param command String parsed by Parser object.
     * @param isExit Boolean to decide whether to shut down chat bot.
     */
    public Command(String command, boolean isExit) {
        this.command = command;
        this.isExit = isExit;
    }

    /**
     * Gets the boolean object to decide if bot should be shut down.
     * @return Boolean object.
     */
    public boolean isExit() {
        return isExit;
    }

    public abstract void execute(Storage storage, TaskList tasks, Ui ui)
            throws IOException, DukeEmptyArgumentException,
            DukeInvalidCommandException, DukeInvalidDateException,
            DukeInvalidArgumentException, DukeInvalidTaskException, DukeEmptyDescriptionException;
}
