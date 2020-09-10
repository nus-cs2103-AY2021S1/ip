package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * {@code ListCommand} is a child of {@code Command} object.
 *      On execution, it will display all the tasks currently in the task list.
 */
public class ListCommand extends Command {

    protected ListCommand() { }

    /**
     * Creates a factory method which returns the type of ListCommand based on the input.
     *
     * @param input user's input / selection for list command
     * @return a ListCommand or ListByPriorityCommand object.
     */
    public static ListCommand getListCommand(String input) {
        //Possible types of list command:
        //list (default list command to show all the task in the list)
        //list -priority (shows list command with priority from HIGHEST to LOWEST)
        //list -priority:rev (shows list command with priority from LOWEST to HIGHEST)

        if (input == null || input.isBlank()) {
            return new ListCommand();
        }

        if (input.contains("priority")) {
            boolean isReversedPriority = false;
            if (input.contains("priority:rev")) {
                isReversedPriority = true;
            }
            return new ListByPriorityCommand(isReversedPriority);
        }

        return new ListCommand(); //no one should reach here. All invalid settings will display the default list.
    }

    /**
     * Displays all the tasks currently in the task list.
     *
     * @param tasks The list of task.
     * @param ui The displaying user interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        assert(tasks != null && ui != null);

        ui.printList(tasks);
    }
}
