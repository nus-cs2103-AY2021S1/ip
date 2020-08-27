package duckie.command;

import duckie.Storage;
import duckie.Ui;
import duckie.exception.DuckieException;
import duckie.exception.DuckieInsufficientInfoException;
import duckie.task.Task;
import duckie.task.TaskList;

import java.util.ArrayList;

/**
 * Command to retrieve all Tasks containing the keyword
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Instantiate a FindCommand object
     * @param keyword Keyword that is going to be matched
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Find the matching tasks and display them
     * @param tasks TaskList containing all the tasks
     * @param ui Ui to interact with the users
     * @param storage Storage to write to File
     * @throws DuckieException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        ArrayList<Task> filteredList = tasks.filterList(keyword);
        if (filteredList.size() == 0) {
            throw new DuckieException("Duckie can't find any matching tasks!");
        } else {
            ui.displayMatchingTasksReply(filteredList);
        }
    }

}
