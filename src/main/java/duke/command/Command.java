package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeEmptyArgumentException;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeInvalidTaskException;

/**
 * Represents a command.
 */
public abstract class Command {

    protected static final int PARSE_INDEX = 1;
    protected static final int TASK_INDEX = 1;

    protected String command;
    protected String extra;
    protected boolean isExit;

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

    /**
     * Executes the command input by user.
     * @param storage Storage object in chat bot.
     * @param tasks TaskList object in chat bot.
     * @param ui Ui object in chat bot.
     * @return String of the Ui text printed by chat bot.
     * @throws IOException If error in loading file.
     * @throws DukeEmptyArgumentException If argument of command is empty.
     * @throws DukeInvalidCommandException If command is unrecognised by chat bot.
     * @throws DukeInvalidDateException If date input format is invalid.
     * @throws DukeInvalidArgumentException If argument for command is unrecognised
     * by chat bot.
     * @throws DukeInvalidTaskException If task is not found in the list.
     * @throws DukeEmptyDescriptionException If description of command is empty.
     */
    public abstract String execute(Storage storage, TaskList tasks, Ui ui)
            throws IOException, DukeEmptyArgumentException,
            DukeInvalidCommandException, DukeInvalidDateException,
            DukeInvalidArgumentException, DukeInvalidTaskException, DukeEmptyDescriptionException;
}
