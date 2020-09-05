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

    /**
     * Lists the tasks presents.
     * The numbering of the tasks may not be contiguous.
     *
     * @param taskList The taskList to operate with.
     * @param storage The storage to operate with.
     * @return A string representing the tasks present with their index
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String tasksString = taskList.getTaskList();

        if (tasksString.equals("")) {
            return "You have no tasks. Add some here!";
        }

        return tasksString;
    }
}
