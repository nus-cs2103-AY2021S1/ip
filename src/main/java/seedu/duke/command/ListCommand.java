package seedu.duke.command;

import seedu.duke.task.Task;
import seedu.duke.TaskList;
import seedu.duke.ui.Ui;

/**
 * Class that represents the list command.
 */
public class ListCommand extends Command {
    public ListCommand() {
        super();
    }

    /**
     * Lists the current tasks in the task list.
     * @param ls The current list of tasks.
     * @param ui The ui that takes of printing output.
     */
    @Override
    public void execute(TaskList ls, Ui ui) {
        ui.printResult("Here are the tasks in your list:");

        for (Task task : ls.getList()) {
            ui.printResult(((ls.indexOf(task) + 1) + ". " + task.getStatus()));
        }
    }
}
