package duke.command;

import duke.logic.Tasklist;
import duke.logic.Storage;

/**
 * Represents a Command to display all Tasks from the task list.
 */
public class ListCommand extends Command {

    /**
     * Creates a Command to display all Tasks from the task list.
     */
    public ListCommand() {
        super();
    }

    /**
     * Displays all the tasks from the task list onto the user interface.
     *
     * @param tasks The task list.
     * @param storage The Storage object that saves the task list.
     * @return The list of all tasks as a String.
     */
    @Override
    public String execute(Tasklist tasks, Storage storage) {
        return tasks.toDisplayString();
    }

    /**
     * Returns false as it is not an ExitCommand.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
