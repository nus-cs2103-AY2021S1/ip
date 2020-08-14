import command.AddCommand;
import command.Command;
import command.ExitCommand;
import command.ListCommand;

/**
 * A Parser object takes in user input and
 * find correct type of command for the input
 */
public class CommandReader {

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
