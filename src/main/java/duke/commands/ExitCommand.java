package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Represents an Exit command that causes Duke to terminate.
 */
public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";

    /**
     * Creates instance of ExitCommand.
     */
    public ExitCommand() {
        super("", true);
    }

    /**
     * Executes the command.
     * Saves all current tasks in the list to the hard drive.
     * Prints goodbye message.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        storage.saveData(taskList, ui);
        ui.displayGoodbye();
    }
}
