package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.Task;
import duke.Ui;

/**
 * Implements the <code>Command</code> interface. <code>AddCommand</code> executes a command
 * to add a specific task as specified by the user.
 */
public class AddCommand implements Command {
    Task t;

    public AddCommand(Task t) {
        this.t = t;
    }

    /**
     * Executes a command to add the specified task as requested by user.
     *
     * @param command String representation of the command to be executed
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @throws DukeException if system fails to add the specified task
     */
    public void execute(String command, Storage storage, Ui ui) throws DukeException {
        storage.save(t);
    }

    /**
     * Compares this <code>AddCommand</code> to the specified object. The result is true if and only if the argument
     * is not null and is an object that represents the same instance as this object.
     *
     * @param o Object to compare this <code>AddCommand</code> against
     * @return true if the given object is an instance of this <code>AddCommand</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else return o instanceof AddCommand;
    }
}
