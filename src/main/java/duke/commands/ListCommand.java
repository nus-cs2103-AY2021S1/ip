package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to list all tasks in the task list.
 *
 */
public class ListCommand extends Command{
    public ListCommand() {
        super();
    }

    /**
     * Executes main logic to list tasks in the task list.
     * Displays current tasks and their respective status to users.
     *
     * @param ui Ui used to generate messages to users.
     * @param listStorage Backend storage to store items in the task list.
     * @param taskList List of tasks added by users so far.
     */
    @Override
    public void execute(Ui ui, Storage listStorage, TaskList taskList) {

        ui.listItems(taskList);
    }
}
