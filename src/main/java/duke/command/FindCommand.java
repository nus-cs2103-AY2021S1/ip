package duke.command;

import duke.DukeStateManager;
import duke.task.NumberedTask;
import duke.task.TaskList;
import duke.ui.Response;
import duke.ui.Ui;
import duke.Storage;

import java.util.List;

/**
 * Represents a search for Tasks matching a keyword. Created by using "find keyword"
 */
public class FindCommand extends Command {

    private final String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    /**
     * Searches through all Tasks in the TaskList and gets all Tasks whose description matches the given searchString
     * and format a String to display all found Tasks to the user.
     *
     * @param tasks TaskList containing all tasks
     * @param ui Ui for formatting of message Strings to be displayed to user
     * @param storage Storage to retrieve and store Tasks entered by user
     * @param dukeStateManager DukeStateManager to manage the current state of Duke
     * @return Response object containing the formatted feedback String to be displayed by the GUI
     */
    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage, DukeStateManager dukeStateManager) {
        List<NumberedTask> foundTasks = tasks.getMatchingTasks(searchString);
        String message = ui.foundTasksToString(foundTasks);
        return new Response(false, ui.formatMessage(message));
    }

}
