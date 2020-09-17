package rogue.logic.directives;

import rogue.model.report.Report;
import rogue.model.task.Task;
import rogue.model.task.TaskList;
import rogue.storage.Storage;
import rogue.ui.Ui;

import java.util.List;

/**
 * Finds {@code Task} from the {@code TaskList}.
 */
public class FindDirective implements Executable {
    /** Message for when the search yields no results. */
    private static final String MESSAGE_FIND_FAILURE = "Of course nothing is found. "
            + "Don't you even remember the tasks you added?";

    /** Message for when there is at least one search result. */
    private static final String MESSAGE_FIND_SUCCESS = "What? Is nap time over? Here are your tasks then:";

    private final String description; // Defaults to ""
    private final int numOfDays; // Defaults to maximum value of integer

    /**
     * Constructs a {@code FindDirective}.
     *
     * Searches for tasks using their descriptions. Matches all tasks
     * that have the search term in their descriptions.
     *
     * @param description The search term.
     */
    public FindDirective(String description) {
        this.description = description;
        this.numOfDays = Integer.MAX_VALUE;
    }

    /**
     * Constructs a {@code FindDirective}.
     *
     * Searches for tasks using their dates. Matches all tasks that are due
     * in a certain number of days (relative to system date).
     *
     * @param numOfDays The number of days.
     */
    public FindDirective(int numOfDays) {
        this.description = "";
        this.numOfDays = numOfDays;
    }

    /**
     * Constructs a {@code FindDirective}.
     *
     * Searches for tasks using both their descriptions and dates.
     * Matches all tasks that have the search term in their descriptions
     * and are due in a certain number of days (relative to system date).
     *
     * @param description   The search term.
     * @param numOfDays     The number of days (from system date).
     */
    public FindDirective(String description, int numOfDays) {
        this.description = description;
        this.numOfDays = numOfDays;
    }

    /**
     * Finds {@code Task} with descriptions containing the search term.
     *
     * @param storage   An instance of {@code Storage}.
     * @param tasks     The {@code TaskList} to search for matching {@code Task}.
     * @param ui        An instance of {@code Ui}.
     * @return A {@code Report} containing the tasks that match the search
     */
    @Override
    public Report execute(Storage storage, TaskList tasks, Ui ui) {
        boolean hasDescription = !description.equals("");
        boolean hasDay = numOfDays != Integer.MAX_VALUE;

        assert hasDescription || hasDay : "At least one search condition must be present!";

        TaskList searchResults;

        if (hasDescription && hasDay) {
            searchResults = tasks.searchByDescription(description)
                    .searchByDays(numOfDays);
        } else if (hasDay) {
            searchResults = tasks.searchByDays(numOfDays);
        } else {
            searchResults = tasks.searchByDescription(description);
        }

        if (searchResults.getTaskList().isEmpty()) {
            return new Report(MESSAGE_FIND_FAILURE);
        } else {
            return generateReport(searchResults.getTaskList());
        }
    }

    private Report generateReport(List<Task> matchingTasks) {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format(MESSAGE_FIND_SUCCESS, description));
        builder.append("\n");
        for (Task task : matchingTasks) {
            builder.append(task);
            builder.append("\n");
        }

        return new Report(builder.toString().trim());
    }
}
