package duke.command;

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

    @Override
    public Response execute(TaskList tasks, Ui ui, Storage storage) {
        List<NumberedTask> foundTasks = tasks.getMatchingTasks(searchString);
        String message = ui.foundTasksToString(foundTasks);
        return new Response(false, ui.formatMessage(message));
    }

}
