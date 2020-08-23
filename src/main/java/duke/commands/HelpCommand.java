package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

/**
 * Represents a help command when the user wants to see
 * the features Duke offers.
 */
public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "--help";

    /**
     * Creates an instance of a HelpCommand.
     */
    public HelpCommand() {
        super("Help command called", false);
    }

    /**
     * Executes the command.
     * Shows the help menu.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.showHelp();
    }
}
