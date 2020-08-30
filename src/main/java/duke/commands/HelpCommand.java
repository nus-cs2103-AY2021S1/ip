package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to print commands available in the bot.
 *
 */
public class HelpCommand extends Command {
    /**
     * Creates a help command object.
     *
     */
    public HelpCommand() {
        super();
    }

    /**
     * Executes the main logic of displaying help message to user.
     *
     * @param ui
     * @param listStorage
     * @param taskList
     */
    @Override
    public void execute(Ui ui, Storage listStorage, TaskList taskList) {
        ui.helpMessage();
    }
}
