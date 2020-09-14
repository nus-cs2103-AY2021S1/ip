package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.tasklist.TaskList;

/**
 * Handles the listing of tasks stored in the chatbot.
 */
public class ListCommand implements Command {

    /**
     * Lists out all the tasks in the chatbot to the user in  a proper format.
     *
     * @param tasks   TaskList.
     * @param ui      Ui.
     * @param storage Storage.
     * @return The list message by the Ui.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.list(tasks.getPlanner());
    }
}
