package duke.core.command;

import java.util.List;
import java.util.logging.Logger;

import duke.core.task.Task;
import duke.designpattern.command.Executable;

/**
 * List all items in taskList
 */
public class ListCommand implements Executable {

    private static final Logger logger = Logger.getLogger(ListCommand.class.getName());

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

        logger.info(ListCommand.class.getSimpleName() + ": Listing tasks");

        // taskList is empty, Inform user and return
        if (taskList.isEmpty()) {
            System.out.println("List is empty!");
            logger.info(ListCommand.class.getSimpleName() + ": List is empty");
            return;
        }

        // taskList is not empty, Iterate and print all items in taskList
        int index = 0;
        for (Task task : taskList) {
            System.out.println(++index + ". " + task.toString());
            logger.fine(ListCommand.class.getSimpleName() + ": List " + task.toString());
        }

    }

}
