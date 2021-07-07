package duke.command;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.Comparator;

/**
 * {@code ListByPriorityCommand} is a child of {@code ListCommand} object.
 *      On execution, it will display all the tasks currently in the task list sorted by Priority.
 */
public class ListByPriorityCommand extends ListCommand {
    /** A boolean to check if user wants a reverse sort Priority */
    private boolean isReversePriority;

    /**
     * Constructs a ListByPriorityCommand which sorts and prints the list.
     *
     * @param isReversePriority Sorting order. true = reverse sorting order (Lowest Priority to Highest Priority)
     */
    public ListByPriorityCommand(boolean isReversePriority) {
        this.isReversePriority = isReversePriority;
    }

    /**
     * Displays all the tasks currently in the task list sorted by priority.
     *
     * @param tasks The list of task.
     * @param ui The displaying user interface.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        assert(tasks != null && ui != null);

        Comparator<Task> compareFunction = (Task a, Task b) -> {
            Integer taskAPriorityValue = a.getPriority().getPriorityValue();
            Integer taskBPriorityValue = b.getPriority().getPriorityValue();
            return taskAPriorityValue.compareTo(taskBPriorityValue);
        };

        if (isReversePriority) {
            compareFunction = compareFunction.reversed();
        }

        TaskList sortedList = tasks.sort(compareFunction);
        ui.printList(sortedList);
    }
}
