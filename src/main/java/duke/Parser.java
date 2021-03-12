package duke;

import duke.command.*;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidInputException;

/**
 * Parser class to parse user input into respective command lines.
 */
public class Parser {

    /**
     * Parse user input into useful commands.
     *
     * @param userInput String input by user through the Ui.
     * @return respective command that will be executed.
     * @throws DukeException exceptions thrown by parsing user inputs.
     */
    public static Command parseCommand(String userInput) throws DukeException {
        if (userInput.startsWith("done")) {
            return new DoneCommand(userInput);
        } else if (userInput.startsWith("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInput.equals("bye")) {
            return new ExitCommand(userInput);
        } else if (userInput.equals("list")) {
            return new ListCommand(userInput);
        } else if (userInput.startsWith("find")) {
            return new FindCommand(userInput);
        } else if (userInput.startsWith("help")) {
            return new HelpCommand(userInput);
        } else if (userInput.contains("|")) {
            throw new InvalidInputException("'|' character not allowed.");
        } else {
            return addCommand(userInput);
        }
    }

    /**
     * Differentiate user input into commands that add their respective
     * tasks into the task list.
     *
     * @param command user input.
     * @return respective Command.
     * @throws DukeException unable to read user input.
     */
    public static Command addCommand(String command) throws DukeException {
        if (command.startsWith("todo")) {
            return new TodoCommand(command);
        } else if (command.startsWith("deadline")) {
            return new DeadlineCommand(command);
        } else if (command.startsWith("event")) {
            return new EventCommand(command);
        } else {
            throw new DukeException("OOPS!!! I'm sorry,"
                    + " but I don't know what that means :-(");
        }
    }
}
