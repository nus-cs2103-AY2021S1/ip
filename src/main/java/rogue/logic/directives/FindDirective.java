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
    private static final String MESSAGE_FIND_FAILURE = "oF CoUrSe %s iS NoT FoUnD. "
            + "DoN'T YoU EvEn rEmEmBeR ThE TaSkS YoU AdDeD?";

    /** Message for when there is at least one search result. */
    private static final String MESSAGE_FIND_SUCCESS = "wHaT? iS NaP TiMe oVeR? hErE ArE YoUr tAsKs tHeN:";

    private final String searchTerm;

    public FindDirective(String searchTerm) {
        this.searchTerm = searchTerm;
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
        List<Task> matchingTasks = tasks.search(searchTerm);

        if (matchingTasks.isEmpty()) {
            return new Report(MESSAGE_FIND_FAILURE);
        } else {
            StringBuilder builder = new StringBuilder();

            builder.append(String.format(MESSAGE_FIND_SUCCESS, searchTerm));
            builder.append("\n");
            for (Task task : matchingTasks) {
                builder.append(task);
            }

            return new Report(builder.toString());
        }
    }
}
