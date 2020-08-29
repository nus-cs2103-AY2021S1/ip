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
     * @param command String representation of the command to be executed
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @throws DukeException if command fails to execute
     */
    String execute(String command, Storage storage, Ui ui, TaskList taskList) throws DukeException;

}
