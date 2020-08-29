package duke.command;

import duke.Storage;
import duke.exception.DukeException;
import duke.tool.TaskList;

/**
 * Represents a command to exit from Duke.
 */
public class ExitCommand implements Command {
    /**
     * Exit message
     */
    private static final String EXIT_MESSAGE = "Bye ! Hope to see you again soon.";

    @Override
    public void execute(TaskList tasks, Storage storage) throws DukeException {
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getResponse() {
        return ExitCommand.EXIT_MESSAGE;
    }

}
