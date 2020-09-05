package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Implements the <code>Command</code> interface. <code>HelpCommand</code>
 * specifies a list of all commands format to user.
 */
public class HelpCommand implements Command {

    /**
     * Executes a command to print format of all commands.
     *
     * @param storage Storage of this <code>Duke</code>
     * @param ui Ui containing all prints for user interactions
     * @param taskList List of task for this <code>Duke</code>
     * @return a string representation of the message informing user if the command has been successfully executed
     */
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        String userGuide = "Enter the following to execute the corresponding commands:\n";
        String end = "That's all! Have fun!!! *Woof woof*";
        return userGuide + AddCommand.commandToExecute() + DeleteCommand.commandToExecute()
                + DoneCommand.commandToExecute() + UndoCommand.commandToExecute()
                + FindCommand.commandToExecute() + CheckCommand.commandToExecute()
                + ListCommand.commandToExecute() + ExitCommand.commandToExecute()
                + commandToExecute() + end;
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
        return " help : list all commands\n";
    }

}
