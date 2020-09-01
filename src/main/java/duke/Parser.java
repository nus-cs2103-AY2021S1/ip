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
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.length() >= 4 && input.startsWith("done")) {
            return new DoneCommand(input.substring(4));
        } else if (input.length() >= 6 && input.startsWith("delete")) {
            return new DeleteCommand(input.substring(6));
        } else if (input.length() >= 4 && input.startsWith("todo")) {
            return new TodoCommand(input.substring(4));
        } else if (input.length() >= 5 && input.startsWith("event")) {
            return new EventCommand(input.substring(5));
        } else if (input.length() >= 8 && input.startsWith("deadline")) {
            return new DeadlineCommand(input.substring(8));
        } else if (input.length() >= 4 && input.startsWith("find")) {
            return new FindCommand(input.substring((4)));
        } else {
            throw new InvalidInputException();
        }
    }
}
