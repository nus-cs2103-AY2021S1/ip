package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

/**
 * Encapsulates data and methods specific to the Find command.
 */
public class FindCommand extends Command {

    private final String argument;

    /**
     * Creates a new instance of the Find command class.
     * @param argument Argument passed in by the user.
     */
    public FindCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Filters the task list by a certain keyword and prints to console.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list that needs to be filtered.
     * @param ui UI object for the instance of Duke.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) {

        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Task list cannot be null";

        ui.printToConsole(taskList.taskListToKeywordFilteredString(argument));
    }
}
