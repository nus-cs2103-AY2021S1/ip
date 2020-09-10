package cartona.command;

import cartona.Storage;
import cartona.exception.CartonaException;
import cartona.task.TaskList;
import cartona.ui.Ui;

/**
 * The Command class is an interface for classes that are used to run commands from the user.
 *
 * @author Jaya Rengam
 */
public interface Command {
    /**
     * Executes the command using the given arguments.
     *
     * @param taskList The TaskList being manipulated by the Command
     * @param ui The Ui object that is used to print the action to the console.
     * @param storage The Storage object used to update the text file, if the TaskList is being modified.
     * @return The string to be printed to the console upon execution of the command.
     * @throws CartonaException if there is an error during execution of the command.
     */
    String execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException;

    /**
     * Get a boolean describing whether the command is an exit command.
     */
    boolean isExitCmd();
}
