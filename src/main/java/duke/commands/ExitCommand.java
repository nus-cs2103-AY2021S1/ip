package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

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
     * Executes the command and returns Duke's response.
     * Saves all current tasks in the list to the hard drive.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     * @return String of Duke response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert ui != null;
        assert storage != null;
        storage.saveData(taskList, ui);
        return ui.displayGoodbye();
    }
}
