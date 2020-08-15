import command.AddCommand;
import command.Command;
import command.DoneCommand;
import command.ExitCommand;
import command.ListCommand;

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

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(words[1]);
        case "bye":
            return new ExitCommand();
        default:
            return new AddCommand(userInput);
        }
    }
}
