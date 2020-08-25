package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command to exit the bot.
 *
 */
public class ByeCommand extends Command {

    /**
     * Represents a command to exit the bot.
     *
     */
    public ByeCommand () {
        super();
        this.isExit = true;
    }

    /**
     * Executes main logic to exit the bot.
     * Displays bye message to user.
     *
     * @param ui Ui used to generate messages to users.
     * @param listStorage Backend storage to store items in the task list.
     * @param taskList List of tasks added by users so far.
     */
    @Override
    public void execute(Ui ui, Storage listStorage, TaskList taskList) {
        ui.goodBye();
    }
}
