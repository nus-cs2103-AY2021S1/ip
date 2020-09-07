package duke.command;

import duke.utils.DukeException;
import duke.utils.DukeFileHandler;
import duke.utils.TaskList;
import duke.utils.Ui;

import java.util.List;


/**
 * Corresponds to commands the users wants to execute.
 */
public abstract class Command {
    protected static List<String> aliases;


    /**
     * Uses polymorphism to execute the methods relevant to the Command.
     *
     * @param tasks       TaskList object which contains the updated list.
     * @param ui          The reference to ui, for updating the ui.
     * @param fileHandler Contains methods to update the file.
     * @throws DukeException Throws DukeException which must be caught by the method.
     */
    public abstract void execute(TaskList tasks, Ui ui, DukeFileHandler fileHandler) throws DukeException;

    // todo create a getter to return the alias for the loop

    // todo return a COMMAND_TYPE.
    public static CommandType containsKeyword(String commandKeyword) {
        // todo loop through all the commands types.
        // todo for each command type, loop through the alias.
        if (contains(ClearCommand.aliases, commandKeyword)) {
            return CommandType.CLEAR;
        } else if (contains(DeadlineCommand.aliases, commandKeyword)) {
            return CommandType.DEADLINE;
        } else if (contains(DeleteCommand.aliases, commandKeyword)) {
            return CommandType.DELETE;
        } else if (contains(DoneCommand.aliases, commandKeyword)) {
            return CommandType.DONE;
        } else if (contains(EventCommand.aliases, commandKeyword)) {
            return CommandType.EVENT;
        } else if (contains(ExitCommand.aliases, commandKeyword)) {
            return CommandType.EXIT;
        } else if (contains(FindCommand.aliases, commandKeyword)) {
            return CommandType.FIND;
        } else if (contains(ListCommand.aliases, commandKeyword)) {
            return CommandType.LIST;
        } else if (contains(TodoCommand.aliases, commandKeyword)) {
            return CommandType.TODO;
        } else {
            return CommandType.UNKNOWN;
        }

    }


    private static boolean contains(List<String> aliases, String commandKeyword) {
        for (String alias : aliases) {
            if (alias.equals(commandKeyword)) {
                return true;
            }
        }
        return false;
    }


}
