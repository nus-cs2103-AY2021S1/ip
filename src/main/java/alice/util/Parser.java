package alice.util;

import alice.command.CommandType;
import alice.command.InvalidCommandException;
import alice.command.types.Command;

/**
 * Represents a parser that makes sense of user input.
 */
public class Parser {
    /**
     * Parses the user input into the appropriate commands.
     *
     * @param userInput the input from user.
     * @return the appropriate command indicated by the user.
     * @throws InvalidCommandException if the userInput does not match any commands and/or its command signature.
     */
    public static Command parseCommand(String userInput) throws InvalidCommandException {
        // Split the userInput into the command word and subsequent command details
        String[] arr = userInput.strip().split(" ", 2);
        String cmd = arr[0];
        String argument = arr.length == 2 ? arr[1] : "";

        // Iterate through the command types
        CommandType[] commands = CommandType.values();
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].hasCommandWord(cmd)) {
                return commands[i].createCmd(argument);
            }
        }
        throw new InvalidCommandException("Sorry I cannot register that command!\n"
                + "Use 'help' command to see the lists of available command");
    }
}
