package duke.command;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to search for tasks using a keyword.
 */
public class FindByKeywordCommand extends Command {

    /** String containing the search keyword. */
    private final String searchKeyword;

    /**
     * Creates and initialises a new FindByKeywordCommand.
     *
     * @param searchKeyword String that contains the search keyword.
     */
    public FindByKeywordCommand(String searchKeyword) {
        this.searchKeyword = searchKeyword;
        assert !searchKeyword.isBlank() : "search keyword cannot be empty";
    }

    /**
     * Performs the operation of searching for all the tasks in the user's list
     * of tasks that contains the keyword provided for the search.
     *
     * @param tasks List of tasks belonging to the user.
     * @param ui Ui object created for the Duke object to handle user interactions.
     * @param storage Storage object used by the Duke object for file operations.
     * @return String containing a list of tasks that contains the search keyword.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String keyword = this.searchKeyword.toLowerCase();
        Predicate<Task> searchKeywordFilter = task -> {
            String taskDescription = task.getDescription().toLowerCase();
            return taskDescription.contains(keyword);
        };
        List<Task> searchResults = new ArrayList<>(tasks.getTaskList())
                .stream()
                .filter(searchKeywordFilter)
                .collect(Collectors.toList());
        return ui.showTaskList(searchResults);
    }

    /**
     * Indicates if the Duke session has ended.
     *
     * @return False since the Duke session has not been terminated.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
