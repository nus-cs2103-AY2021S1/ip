package duke.command;

import duke.Storage;
import duke.UI;
import duke.task.TaskList;

/**
 * Encapsulates data and methods specific to the Bye command.
 */
public class ByeCommand extends Command {

    /**
     * Creates a new instance of the Bye command class.
     */
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
        ui.printToConsole("Goodbye!");
    }
}
