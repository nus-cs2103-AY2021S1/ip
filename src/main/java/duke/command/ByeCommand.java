package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Encapsulates a command that will stop the chat bot.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command by printing out a goodbye message.
     *
     * @param taskList          The list of tasks known by the chat bot.
     * @param ui                The Ui that is used by the chat bot.
     * @param storage           The storage used by the chat bot.
     * @return                  Chat bot message
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showGoodbye();
    }

    /**
     * Gives a boolean to tell the bot to stop.
     *
     * @return boolean that will break the while loop.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
