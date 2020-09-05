package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * <code>Command</code> interface provides a <code>execute</code> method to execute the specified command. To
 * implement this interface, it is necessary to provide a definition for the <code>execute</code> method.
 */
public interface Command {
    /**
     * Executes this <code>Command</code>.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     * @throws DukeException if command fails to execute
     */
    String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException;

    /**
     * Undo this command.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     * @throws DukeException if system fails to undo this action
     */
    String undo(Storage storage, Ui ui, TaskList taskList) throws DukeException;

}
