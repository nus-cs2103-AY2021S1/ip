package duke.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import duke.commands.CommandResult;
import duke.data.task.Task;

/**
 * UI of the application.
 */
public class Ui {

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = " %1$d. %2$s";

    /**
     * Returns a string of a list of tasks to the user, formatted as an indexed list.
     */
    public String getTaskListView(List<? extends Task> taskList) {
        final List<String> formattedTasks = new ArrayList<>();
        taskList.forEach(task -> formattedTasks.add(task.toString()));
        return getIndexedListForViewing(formattedTasks);
    }

    /**
     * Shows the result of a command execution to the user. Includes additional formatting to demarcate different
     * command execution segments.
     */
    public String getResultToUser(CommandResult result) {
        final Optional<List<? extends Task>> tasks = result.getRelevantTasks();
        StringBuilder sb = new StringBuilder();
        tasks.ifPresent(list -> sb.append(getTaskListView(list)));
        sb.append(result.feedbackToUser);
        return sb.toString();
    }

    /** Formats a list of strings as a viewable indexed list. */
    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }

}
