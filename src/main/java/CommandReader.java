import command.Command;
import command.DeadlineCommand;
import command.DoneCommand;
import command.ErrorCommand;
import command.EventCommand;
import command.ExitCommand;
import command.ListCommand;
import command.TodoCommand;
import exception.DukeException;

/**
 * A CommandReader object to parse the user input.
 */
public class CommandReader {
    /**
     * Read in user input and identify the
     * correct type of command for the input.
     *
     * @param userInput a String from user's input.
     * @return a Command to be processed by the agent.
     */
    public Command readCommand(String userInput) {
        String[] words = userInput.split(" ");
        String commandWord = words[0];
        String content = words.length == 1 ? "" : CommandReader.generateContent(words);

        try {
            switch (commandWord) {
            case "done":
                return new DoneCommand(content);
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
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            return new ErrorCommand(e.getMessage());
        }
    }

    /**
     * Generate content for the input.
     *
     * @param words user input parsed into an array of string.
     * @return a String representing non-commandWord part of the user input
     */
    public static String generateContent(String[] words) {
        String result = words[1];
        for (int i = 2; i < words.length; i++) {
            result = result + " " + words[i];
        }
        return result;
    }
}
