package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Handles the listing of tasks stored in the chatbot.
 */
public class ListCommand implements Command {

    /**
     * Lists out all the tasks in the chatbot to the user in  a proper format.
     * @param tasks TaskList.
     * @param ui Ui.
     * @param storage Storage.
     * @return A boolean to indicate that it is not the terminating command.
     */
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.list(tasks.getPlanner());
        return false;
    }
}
