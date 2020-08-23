package duke.command;

import duke.Storage;
import duke.Ui;

/**
 * Implements the <code>Command</code> interface. <code>ListCommand</code> executes
 * a command to list all <code>Task</code> stored in database.
 */
public class ListCommand implements Command {

    /**
     * Executes a command to list all <code>Task</code> stored in database..
     *
     * @param command String representation of the command to be executed
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     */
    public void execute(String command, Storage storage, Ui ui) {
        storage.printAll();
    }

    /**
     * Compares this <code>ListCommand</code> to the specified object.
     * The result is true if and only if the argument is not null and is an object
     * that represents the same instance as this object.
     *
     * @param o Object to compare this <code>ListCommand</code> against
     * @return true if the given object is an instance of this <code>ListCommand</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else return o instanceof ListCommand;
    }
}
