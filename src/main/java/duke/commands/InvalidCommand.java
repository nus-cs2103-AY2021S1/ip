package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents an Invalid command.
 */
public class InvalidCommand extends Command {
    private final String message = "Invalid command/format! check --help for more info";

    /**
     * Creates an instance of an Invalid Command.
     */
    public InvalidCommand() {
        super("Invalid command", false);
    }

    /**
     * Executes the command.
     * Shows the user an error message.
     *
     * @param taskList List of tasks currently being tracked.
     * @param ui User interface object.
     * @param storage Storage object.
     * @return String of Duke response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert ui != null;
        return ui.showError(message);
    }
}
