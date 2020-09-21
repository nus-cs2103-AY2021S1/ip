package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to guide the user on how to use the bot.
 */
public class HelpCommand extends Command {
    /**
     * Creates a HelpCommand to display help messages.
     */
    public HelpCommand() {
        super();
    }

    /**
     * Displays help messages within the bot and link to User Guide.
     *
     * @param ui Ui object to print messages to user.
     * @param storage Storage object to store items in the TaskList.
     * @param tasks Current list of tasks.
     * @return UI message after executing help command.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        return ui.helpMessage();
    }
}