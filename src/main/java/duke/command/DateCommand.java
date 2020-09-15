package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;

/**
 * Encapsulates data and methods specific to the Date command.
 */
public class DateCommand extends Command {

    private final String argument;

    /**
     * Creates a new instance of the Date command class.
     * @param argument Argument passed in by the user.
     */
    public DateCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Prints a date filtered version of the task list to console.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list that needs to be filtered by date.
     * @param ui UI object for the instance of Duke.
     * @throws DukeException If the argument for filtering the task list by date is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException {

        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Task list cannot be null";

        ui.printToConsole(taskList.taskListToDateFilteredString(argument));
    }
}
