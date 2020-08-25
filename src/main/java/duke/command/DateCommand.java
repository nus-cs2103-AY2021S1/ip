package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class DateCommand extends Command {
    
    private final String argument;

    public DateCommand(String argument) {
        this.argument = argument;
    }

    /**
     * Prints a data filtered version of the task list to console.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list that needs to be filtered by date.
     * @param ui UI object for the instance of Duke.
     * @throws DukeException If the argument for filtering the task list by date is invalid.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) throws DukeException {
        ui.printToConsole(taskList.taskListToDateFilteredString(argument));
    }
}
