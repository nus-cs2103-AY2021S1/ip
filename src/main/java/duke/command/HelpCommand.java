package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DuplicateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidTaskIdException;

/**
 * Represents a help command
 */
public class HelpCommand implements Command {

    /**
     * Shows the available commands.
     *  @param ts
     * @param ui
     * @param input
     * @return list of available commands.
     */
    @Override
    public String execute(TaskList ts, Ui ui, String input) {
        return ui.help();
    }
}
