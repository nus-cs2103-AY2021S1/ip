package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;



/**
 * Class representing a find command.
 */
public class FindCommand extends Command {

    private String keyword;

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
        ui.botOutput(tasks.listTasksWithKeyword(keyword));
    }
}
