package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles exit command of the chatbot.
 */
public class ExitCommand implements Command {

    /**
     * Ends the chatbot and  sends the appropriate response to the user.
     * @param tasks TaskList.
     * @param ui Ui.
     * @param storage Storage.
     * @return A boolean to signify the termination of the chatbot.
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.farewell();
        return true;
    }
}
