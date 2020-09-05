package duke.core.command;

import duke.designpattern.command.Executable;
import duke.core.task.Task;

import java.util.List;

/**
 * List all items in taskList
 */
public class ListCommand implements Executable {

    private final List<Task> taskList;

    /**
     * Create a ListCommand which list all tasks in specified taskList
     * @param taskList A specified taskList of interest
     */
    public ListCommand(List<Task> taskList) {
        this.taskList = taskList;
        assert this.taskList != null;
    }

    /**
     * List all items in taskList
     */
    @Override
    public void execute() {

        // taskList is empty, Inform user and return
        if (taskList.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        // taskList is not empty, Iterate and print all items in taskList
        int index = 0;
        for (Task task : taskList) {
            System.out.println(++index + ". " + task.toString());
        }

    }

}
