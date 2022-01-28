package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DuplicateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidTaskIdException;

/**
 * Represents a quit command.
 */
public class QuitCommand implements Command {

    /**
     * Shows a goodbye message before exiting the program.
     *  @param ts
     * @param ui
     * @param input
     * @return empty string.
     */
    @Override
    public String execute(TaskList ts, Ui ui, String input) {
        System.exit(0);
        return "";
    }
}
