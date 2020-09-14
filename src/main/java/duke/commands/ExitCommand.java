package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasklist.TaskList;

/**
 * Handles exit command of the chatbot.
 */
public class ExitCommand implements Command {

    /**
     * Ends the chatbot and  sends the appropriate response to the user.
     *
     * @param tasks   TaskList.
     * @param ui      Ui.
     * @param storage Storage.
     * @return The exit message by the Ui.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.farewell();
    }
}
