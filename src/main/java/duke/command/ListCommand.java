package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command for Duke to enumerate the entire list of tasks and show it to the user.
 */
public class ListCommand extends Command {
    /**
     * Shows all the tasks currently in Duke's TaskList by printing it out to the console.
     *
     * @param taskList the List containing all the tasks that Duke has stored.
     * @param ui a Ui object for interaction with users.
     * @param storage the database for Duke to save all tasks to the user's local storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.print("Here are the tasks in your list:\n");
        taskList.showAllItems();
    }
}
