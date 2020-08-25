package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

public class ByeCommand extends Command {
    
    public ByeCommand() {
    }

    /**
     * Terminates the program and prints the action to console.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list that is used by the instance of Duke.
     * @param ui UI object for the instance of Duke.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) {
        ui.close();
    }
}
