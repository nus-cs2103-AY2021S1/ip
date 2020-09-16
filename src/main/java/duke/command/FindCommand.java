package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing a find command.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Creates a new {@code FindCommand}.
     * @param keyword Keyword for the search.
     */
    public FindCommand(String keyword) {
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
        assertArgumentsValid(tasks, ui, storage);
        ui.botOutput("Here's what I found:\n" + tasks.listTasksWithKeyword(keyword));
    }
}
