package duke;

import java.io.IOException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;

/**
 * Parser deals with making sense of the user command.
 */
public class Parser {

    /**
     * Handles user input and execute relevant command.
     * @param input  User input
     * @return       Command to execute
     * @throws DukeException
     * @throws IOException
     */
    public static Command parse(String input) throws DukeException {
        boolean isExitCommand = input.equals("bye");
        boolean isListCommand = input.equals("list");
        boolean isDoneCommand = input.length() >= 4 && input.startsWith("done");
        boolean isDeleteCommand = input.length() >= 6 && input.startsWith("delete");
        boolean isTodoCommmand = input.length() >= 4 && input.startsWith("todo");
        boolean isEventCommand = input.length() >= 5 && input.startsWith("event");
        boolean isDeadlineCommand = input.length() >= 8 && input.startsWith("deadline");
        boolean isFindCommand = input.length() >= 4 && input.startsWith("find");

        boolean isInvalidCommand = !(isExitCommand || isListCommand || isDoneCommand || isDeleteCommand
                || isTodoCommmand || isEventCommand || isDeadlineCommand || isFindCommand);

        if (isExitCommand) {
            return new ExitCommand();
        } else if (isListCommand) {
            return new ListCommand();
        } else if (isDoneCommand) {
            return new DoneCommand(input.substring(4));
        } else if (isDeleteCommand) {
            return new DeleteCommand(input.substring(6));
        } else if (isTodoCommmand) {
            return new TodoCommand(input.substring(4));
        } else if (isEventCommand) {
            return new EventCommand(input.substring(5));
        } else if (isDeadlineCommand) {
            return new DeadlineCommand(input.substring(8));
        } else if (isFindCommand) {
            return new FindCommand(input.substring((4)));
        } else {
            assert isInvalidCommand;
            throw new InvalidInputException();
        }
    }
}
