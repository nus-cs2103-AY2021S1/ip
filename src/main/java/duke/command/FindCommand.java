package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Class representing a find command.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Creates a new {@code FindCommand}.
     * @param keyword Keyword for the search.
     * @throws DukeException If no keyword is provided.
     */
    public FindCommand(String keyword) throws DukeException {
        if (keyword == null) {
            throw new DukeException("You have to tell me what to search for!");
        }
        this.keyword = keyword;
    }

    /**
     * Searches and lists tasks with description containing the keyword.
     * @param tasks {@link TaskList} containing list of tasks.
     * @param ui {@link Ui} object.
     * @param storage {@link Storage} object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.botOutput(tasks.listTasksWithKeyword(keyword));
    }
}
