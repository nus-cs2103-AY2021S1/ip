package duke;

import command.AddToListCommand;
import command.Command;
import command.DeleteCommand;
import command.DoneCommand;
import command.FindCommand;
import command.ListCommand;
import command.UndoCommand;
import exception.DukeException;

/**
 * A Parser object deals with making sense of user command.
 *
 * @author ameliatjy
 * @version 2.0
 * @since 2020-09-01
 */
public class Parser {
    private final TaskList currList;

    public Parser(TaskList currList) {
        this.currList = currList;
    }

    /**
     * Looks through the input message to determine actions to be taken.
     *
     * @param inputMsg User's input message to the chat bot.
     * @return Command to be executed.
     * @throws DukeException If command type is invalid.
     */
    public Command processMsg(String inputMsg) throws DukeException {
        // user specified action, to identify type of action
        String actionType = inputMsg.split(" ")[0];

        if (inputMsg.contains("(") || inputMsg.contains(")")) {
            throw new DukeException("Symbol used is not supported by Duke!");
        }

        if (inputMsg.equals("list")) {
            return new ListCommand();
        } else if (actionType.equals("done")) {
            return new DoneCommand();
        } else if (actionType.equals("delete")) {
            return new DeleteCommand();
        } else if (actionType.equals("find")) {
            return new FindCommand();
        } else if (actionType.equals("undo")) {
            return new UndoCommand();
        } else if (actionType.equals("todo") || actionType.equals("deadline") || actionType.equals("event")) {
            return new AddToListCommand();
        } else {
            throw new DukeException("Specified action is not recognised.");
        }
    }
}
