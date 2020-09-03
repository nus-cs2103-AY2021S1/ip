package duke.command;

import duke.task.TaskList;

import duke.ui.Ui;

/**
 * An interface for executable user commands.
 */
public interface Command {
    /**
     * Executes the command and obtains a response String.
     *
     * @param tasks List of user's tasks.
     * @param ui UI of Duke.
     */
    String execute(TaskList tasks, Ui ui);

    /**
     * Returns whether to exit Duke or not.
     *
     * @return true if exit command, false otherwise.
     */
    boolean isExit();
}
