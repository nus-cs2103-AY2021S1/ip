package duke.command;

import duke.ui.Ui;
import duke.task.TaskList;

/**
 * An interface for executable user commands. 
 */
public interface Command {
    /**
     * Executes the command.
     * 
     * @param tasks List of user's tasks.
     * @param ui UI of Duke.
     */
    void execute(TaskList tasks, Ui ui);

    /**
     * Returns whether to exit Duke or not.
     * 
     * @return true if exit command, false otherwise.
     */
    boolean isExit();
}
