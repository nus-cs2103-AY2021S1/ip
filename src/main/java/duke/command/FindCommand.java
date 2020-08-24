package duke.command;

import duke.task.NumberedTask;
import duke.task.TaskList;
import duke.Ui;
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
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<NumberedTask> foundTasks = tasks.getMatchingTasks(searchString);
        String message = ui.foundTasksToString(foundTasks);
        ui.printMessage(message);
    }

}
