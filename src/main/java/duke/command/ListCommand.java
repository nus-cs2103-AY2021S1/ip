package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to list all the Tasks in TaskList.
 */
public class ListCommand extends Command {
    /**
     * Creates a ListCommand.
     */
    public ListCommand() {
        super();
    }

    /**
     * Displays current tasks and their corresponding statuses to user.
     *
     * @param ui Ui object to display messages to user.
     * @param storage Storage object to store items in the TaskList.
     * @param tasks Current list of Tasks.
     * @return Ui message to display the list of Tasks found.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        return ui.listTasks(tasks);
    }

}
