package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * {@code: ListCommand} is a child of {@code: Command} object.
 *      On execution, it will display all the tasks currently in the task list.
 */
public class ListCommand extends Command {

    /**
     * Displays all the tasks currently in the task list.
     *
     * @param tasks The list of task.
     * @param ui The displaying user interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.printList(tasks);
    }
}
