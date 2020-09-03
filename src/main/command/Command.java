package main.command;

import main.exception.InvalidTaskException;
import main.task.TaskList;
import main.ui.Ui;

/**
 * Represents the commands that a user can give to the UI.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.2
 * @since v0.1
 */
public interface Command {
    /**
     * Executes the command and prints out response from the ui and updates
     * the task list accordingly.
     * @param ui the ui used to print out responses.
     * @param tasks the task list.
     * @return the string that indicates the execution of the command.
     * @throws InvalidTaskException if task is not found in list.
     */
    String execute(Ui ui, TaskList tasks) throws InvalidTaskException;

    /**
     * Checks if there are still commands after this command
     * @return false if ExitCommand, else true.
     */
    boolean hasCommandAfter();
}
