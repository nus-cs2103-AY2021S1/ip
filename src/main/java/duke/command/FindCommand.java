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
    
    /*
     * Creates a new FindCommand.
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
     * @param tasks List of tasks.
     * @param ui Ui object.
     * @param storage Storage object.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.botOutput(tasks.listTasksWithKeyword(keyword));
    }
}
