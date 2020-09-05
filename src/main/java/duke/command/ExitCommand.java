package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Implements the <code>Command</code> interface. <code>ExitCommand</code> executes
 * a command to close the <code>Scanner</code> object and terminate program.
 */
public class ExitCommand implements Command {

    /**
     * Executes a command to close <code>Scanner</code> object and terminate program.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param taskList List of task for this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @return a string representation of the message informing user if the command has been successfully executed
     */
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        return " Bye. Hope to see you again soon! *Woof woof*\n";
    }

    /**
     * Undo this command.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     */
    public String undo(Storage storage, Ui ui, TaskList taskList) {
        return " There's no need to undo this action! *woof*\n";
    }

    /**
     * Returns a string representation informing user how to execute this command.
     *
     * @return a string representation informing users how to execute this command
     */
    public static String commandToExecute() {
        return " bye : terminates the program\n";
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
        } else {
            return o instanceof ExitCommand;
        }
    }
}
