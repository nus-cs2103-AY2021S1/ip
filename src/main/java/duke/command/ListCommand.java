package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DuplicateException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidTaskIdException;

/**
 * Represents a list command.
 */
public class ListCommand implements Command {

    /**
     * Shows all the tasks that are in the list.
     *  @param ts
     * @param ui
     * @param input
     * @return list of tasks.
     */
    @Override
    public String execute(TaskList ts, Ui ui, String input) {
        return ui.list();
    }
}
