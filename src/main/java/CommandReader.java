import command.AddCommand;
import command.Command;
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
        if (userInput.equals("list")) {
            return new ListCommand(userInput);
        } else if (userInput.equals("bye")) {
            return new ExitCommand(userInput);
        } else {
            return new AddCommand(userInput);
        }
    }
}
