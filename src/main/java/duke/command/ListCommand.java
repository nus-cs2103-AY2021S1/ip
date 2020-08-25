package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
    }

    /**
     * Prints the task list to console.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list that will be printed.
     * @param ui UI object for the instance of Duke.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) {
        ui.printToConsole(taskList.convertTaskListToString());
    }
    
}
