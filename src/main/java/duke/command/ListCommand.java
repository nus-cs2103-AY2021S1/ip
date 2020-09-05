package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Encapsulates the logic for listing tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand.
     *
     * @param args Arguments are unused.
     */
    public ListCommand(String args) {
        super(args);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        String tasksString = taskList.getTaskList();

        if (tasksString.equals("")) {
            return "You have no tasks. Add some here!";
        }

        return tasksString;
    }
}
