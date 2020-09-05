package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Implements the <code>Command</code> interface. <code>UndoCommand</code> undo
 * a previous <code>Command</code> specified.
 */
public class UndoCommand implements Command {
    private final Command prevCommand;

    /**
     * Creates an <code>UndoCommand</code> containing a previous <code>Command</code>.
     *
     * @param prevCommand the previous command to undo
     */
    public UndoCommand(Command prevCommand) {
        this.prevCommand = prevCommand;
    }

    /**
     * Executes a command to undo a previous command.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     * @throws DukeException if system fails to undo the specified command
     */
    public String execute(Storage storage, Ui ui, TaskList taskList) throws DukeException {
        if (prevCommand == null) {
            return " There's no command to undo! *woof*\n";
        }
        return prevCommand.undo(storage, ui, taskList);
    }

    /**
     * Undo this command.
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
        return " undo : undo an action\n";
    }

}
