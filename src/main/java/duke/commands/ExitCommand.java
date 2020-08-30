package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that exit Duke chat bot.
 * @version 1.0
 */
public class ExitCommand extends Command {
    /**
     * Creates a new ExitCommand.
     */
    public ExitCommand() {
        this.commandName = "Exit";
        this.isExit = true;
    }

    /**
     * Exits the Duke chat bot.
     * Shows action feedback to user.
     *
     * @param list A TaskList object of which the command is executed on.
     * @param ui An UI object to interact with the user if required by the command.
     * @param storage A Storage object to write/access information to/from a file if required by the command.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.showFarewell();
    }
}
