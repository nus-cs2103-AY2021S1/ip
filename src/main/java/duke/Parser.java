package duke;

import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/** A Parser object to parse the user input */
public class Parser {
    /**
     * Reads in user input and identify the correct type of duke.command for the input.
     *
     * @param userInput A String from user's input.
     * @return A Command to be processed by the agent.
     * @throws DukeException If the command construction involves error or the DateTime parsing involves error.
     */
    public static Command parseCommand(String userInput) throws DukeException {
        String[] inputParts = userInput.split(" ");
        String commandWord = inputParts[0];
        String content = Parser.generateContent(inputParts);

        try {
            switch (commandWord) {
            case "done":
                return new DoneCommand(content);
            case "delete":
                return new DeleteCommand(content);
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "todo":
                return new TodoCommand(content);
            case "deadline":
                return new DeadlineCommand(content);
            case "event":
                return new EventCommand(content);
            case "find":
                return new FindCommand(content);
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException | DateTimeParseException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Generates content for the input without the command word.
     *
     * @param inputParts User input parsed into an array of string.
     * @return A String representing the parts of the user input without the command word.
     */
    public static String generateContent(String[] inputParts) {
        assert inputParts.length >= 1 : "Input must not be empty.";

        boolean hasOnlyOneWord = inputParts.length == 1;
        if (hasOnlyOneWord) {
            return "";
        }

        StringBuilder result = new StringBuilder(inputParts[1]);
        for (int i = 2; i < inputParts.length; i++) {
            result.append(" ").append(inputParts[i]);
        }

        return result.toString();
    }
}
