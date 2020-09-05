package duke.core.command;

import duke.designpattern.command.Executable;
import duke.core.task.Task;

import java.util.List;

/**
 * List all tasks which containing searchString
 */
public class FindCommand implements Executable {

    private final List<Task> taskList;
    private final String searchString;

    /**
     * Create a FindCommand to search for specified searchString within the taskList
     * @param taskList to search for searchString
     * @param searchString The string of search interest
     */
    public FindCommand(List<Task> taskList, String searchString) {
        this.taskList = taskList;
        this.searchString = searchString;
        assert this.taskList != null;
        assert this.searchString != null;
    }

    /**
     * List all tasks containing searchString
     */
    @Override
    public void execute() {
        System.out.println("Here are the matching tasks in your list:");

        // Search matching tasks
        int index = 0;
        for (Task task : taskList) {
            if (task.toString().contains(searchString)) {
                System.out.println(++index + ". " + task.toString());
            }
        }

        // Let user know how many items are found, for responsiveness
        System.out.println("Number of tasks found: " + index);
    }

}
