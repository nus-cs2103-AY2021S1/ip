package duke.command;

import duke.Storage;
import duke.Ui;

/**
 * Implements the <code>Command</code> interface. <code>ExitCommand</code> executes
 * a command to close the <code>Scanner</code> object and terminate program.
 */
public class ExitCommand implements Command {

    /**
     * Executes a command to close <code>Scanner</code> object and terminate program.
     *
     * @param command String representation of the command to be executed
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     */
    public void execute(String command, Storage storage, Ui ui) {
        ui.goodBye();
    }

    /**
     * Compares this <code>ExitCommand</code> to the specified object. The result is true if and only if the argument
     * is not null and is an object that represents the same instance as this object.
     *
     * @param o Object to compare this <code>ExitCommand</code> against
     * @return true if the given object is an instance of this <code>ExitCommand</code>, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else return o instanceof ExitCommand;
    }
}
