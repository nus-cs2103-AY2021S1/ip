package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

/**
 * Implements the <code>Command</code> interface. <code>DeleteCommand</code> executes
 * a command to delete a specific task as specified by the user.
 */
public class DeleteCommand implements Command {

    /**
     * Executes a command to delete the specified task as requested by user.
     *
     * @param command String representation of the command to be executed
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @throws DukeException if system fails to delete the specified task
     */
    public void execute(String command, Storage storage, Ui ui) throws DukeException {
        storage.delete(command);
    }

    /**
     * Compares this <code>DeleteCommand</code> to the specified object. The result is true if and only if the argument
     * is not null and is an object that represents the same instance as this object.
     *
     * @param o Object to compare this <code>DeleteCommand</code> against
     * @return true if the given object is an instance of this <code>DeleteCommand</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else return o instanceof DeleteCommand;
    }
}
