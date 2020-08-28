package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;

/**
 * Represents a list command.
 */
public class ListCommand implements Command {
    /**
     * Prints out the list of the user's tasks.
     * 
     * @param tasks List of user's tasks.
     * @param ui UI of Duke.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.displayTaskList(tasks);
    }

    /**
     * Tells Duke to continue running.
     * 
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
