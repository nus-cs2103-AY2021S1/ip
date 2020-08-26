package duke.Commands;

import duke.Storage.Storage;
import duke.Tasks.TaskList;
import duke.Ui.Ui;

/**
 * A command to display the tasks in the task list.
 */
public class ListCommand extends Command{

    /**
     * Executes the command to perform its appropriate action.
     * @param tasks Duke task list.
     * @param ui Ui object to print user messages.
     * @param storage Storage object to load and save tasks to data file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList(ui);
    }
}
