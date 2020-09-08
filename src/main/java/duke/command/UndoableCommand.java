package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * <code>UndoableCommand</code> interface provides a <code>undo</code> method to undo the specified command. To
 * implement this interface, it is necessary to provide a definition for the <code>undo</code> method and methods in
 * its parent class.
 */
public interface UndoableCommand extends Command {

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
