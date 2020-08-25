package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class FindCommand extends Command {

    private final String argument;

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
        ui.printToConsole(taskList.taskListToKeywordFilteredString(argument));
    }
}
