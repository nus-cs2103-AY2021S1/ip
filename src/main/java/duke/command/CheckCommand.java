package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

/**
 * Implements the <code>Command</code> interface. <code>CheckCommand</code> runs
 * a search in the list of <code>Task</code> for <code>Task</code> occurring on this
 * specific date as specified by user.
 */
public class CheckCommand implements Command {

    /**
     * Executes a command to run a search on the list of <code>Task</code> for <code>Task</code>
     * occurring on this specific date.
     *
     * @param command String representation of the command to be executed
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @throws DukeException if system fails to execute search
     */
    public void execute(String command, Storage storage, Ui ui) throws DukeException {
        storage.checkDate(command.substring(5));
    }

    /**
     * Compares this <code>CheckCommand</code> to the specified object. The result is true if and only if the argument
     * is not null and is an object that represents the same instance as this object.
     *
     * @param o Object to compare this <code>CheckCommand</code> against
     * @return true if the given object is an instance of this <code>CheckCommand</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else return o instanceof CheckCommand;
    }
}
