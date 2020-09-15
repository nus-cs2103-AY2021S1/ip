package duke.parser;

import duke.command.*;
import duke.command.FindCommand;

import duke.exception.DukeException;

/**
 * Deals with understanding the input from user and determining Command to execute.
 */
public class Parser {

    /**
     * Determines which Command should be called based on user input.
     *
     * @param userInput the input given by user.
     * @return The Command to be executed.
     * @throws DukeException if a Command is called without required details.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] inputSplit = userInput.split(" ", 2);
        return determineCommand(inputSplit);
    }

    private static Command determineCommand(String[] inputSplit) throws DukeException {
        String userCommand = inputSplit[0];
        if (userCommand.equals("bye")) {
            return new ByeCommand();
        } else if (userCommand.equals("list")) {
            return new ListCommand();
        } else if (userCommand.equals("help")) {
            return new HelpCommand();
        } else if (userCommand.equals("done")) {
            try {
                return new DoneCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify which task you have completed!");
            }
        } else if (userCommand.equals("todo")) {
            try {
                return new TodoCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify your todo description!");
            }
        } else if (userCommand.equals("deadline")) {
            try {
                return new DeadlineCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify your deadline description, date and time!");
            }
        } else if (userCommand.equals("event")) {
            try {
                return new EventCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify your event description, date and time!");
            }
        } else if (userCommand.equals("delete")) {
            try {
                return new DeleteCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify which task you want to delete!");
            }
        } else if (userCommand.equals("filter")) {
            try {
                return new FilterCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify which date you want to filter!");
            }
        } else if (userCommand.equals("find")) {
            try {
                return new FindCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify the keyword to find!");
            }
        } else if (userCommand.equals("weekly")) {
            try {
                return new WeeklyCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please specify your weekly task details!");
            }
        } else if (userCommand.equals("greet")) {
            return new GreetCommand();
        } else {
            return new InvalidCommand();
        }
    }
}
