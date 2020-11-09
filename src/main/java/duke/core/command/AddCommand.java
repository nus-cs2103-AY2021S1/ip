package duke.core.command;

import java.util.List;
import java.util.logging.Logger;

import duke.core.task.Task;
import duke.designpattern.command.CommandException;
import duke.designpattern.command.ReversibleExecutable;

/**
 * Add a task to taskList
 */
public class AddCommand implements ReversibleExecutable {

    private static final Logger logger = Logger.getLogger(AddCommand.class.getName());

    private final List<Task> taskList;
    private final Task task;

    /**
     * Create an AddCommand which adds the Task into the TaskList
     * @param taskList which task will be added to
     * @param task to add into the taskList
     */
    public AddCommand(List<Task> taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
        assert this.taskList != null;
        assert this.task != null;
    }

    /**
     * Add task to end of list
     */
    @Override
    public void execute() {
        // Disallow duplicate
        if (taskList.contains(task)) {
            logger.info(AddCommand.class.getSimpleName() + ": Cannot add duplicate task");
            throw new CommandException("Error: Duplicate Task");
        }

        // Add task
        this.taskList.add(task);
        System.out.println("+ Add: " + task.toString());
        logger.info(AddCommand.class.getSimpleName() + ": Add " + task.toString());
    }

    /**
     * Remove task from list
     */
    @Override
    public void reverse() {
        this.taskList.remove(task);
        System.out.println("- Undo Add: " + task.toString());
        logger.info(AddCommand.class.getSimpleName() + ": Undo Add " + task.toString());
    }

}
