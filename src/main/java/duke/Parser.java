package duke;

import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/**
 * A duke.CommandReader object to parse the user input.
 */
public class Parser {
    /**
     * Read in user input and identify the correct type of duke.command for the input.
     *
     * @param userInput a String from user's input.
     * @return a Command to be processed by the agent.
     * @throws DukeException if the command construction involves error or the DateTimeParsing involves error.
     */
    public static Command parse(String userInput) throws DukeException {
        String[] words = userInput.split(" ");
        String commandWord = words[0];
        String content = words.length == 1 ? "" : Parser.generateContent(words);

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
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException | DateTimeParseException e) {
            throw new DukeException(e.getMessage());
        }
    }

    /**
     * Generate content for the input.
     *
     * @param words user input parsed into an array of string.
     * @return a String representing non-commandWord part of the user input
     */
    public static String generateContent(String[] words) {
        StringBuilder result = new StringBuilder(words[1]);
        for (int i = 2; i < words.length; i++) {
            result.append(" ").append(words[i]);
        }
        return result.toString();
    }
}
