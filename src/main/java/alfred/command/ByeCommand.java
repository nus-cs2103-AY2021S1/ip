package alfred.command;

import alfred.Storage;
import alfred.UI;
import alfred.task.TaskList;

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
     * Displays "Goodbye! The application will close shortly..." on the GUI.
     *
     * @param storage Storage object pointing to the file path where the data is stored.
     * @param taskList Task list that is used by the instance of Alfred.
     * @param ui UI object for the instance of Alfred.
     */
    @Override
    public void execute(Storage storage, TaskList taskList, UI ui) {

        assert ui != null : "UI cannot be null";
        assert storage != null : "Storage cannot be null";
        assert taskList != null : "Task list cannot be null";

        ui.printToConsole("Goodbye! The application will close shortly...");
    }
}
