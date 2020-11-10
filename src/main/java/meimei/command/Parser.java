package meimei.command;

import meimei.botexception.BotException;
import meimei.botexception.NoCommandException;
import meimei.botexception.NoDescriptionException;

/**
 * Static class that parses user commands into executable bot commands represented
 * by <code>Command</code> objects.
 */
public class Parser {

    /**
     * Parse user commands into executable bot commands.
     *
     * @param fullCommand User command (the full line of user input).
     * @return A bot command to be executed.
     * @throws BotException If description is missing for <code>AddCommand</code>,
     *                      <code>DeleteCommand</code> or <code>DoneCommand</code>,
     *                      or if user command does not match any <code>CommandType</code>.
     */
    public static Command parse(String fullCommand) throws BotException {
        String[] commandElements = fullCommand.split(" ", 2);
        String commandString = commandElements[0];
        CommandType commandType = null;

        for (CommandType type : CommandType.values()) {
            if (type.toString().equalsIgnoreCase(commandString)) {
                commandType = type;
                break;
            }
        }

        if (commandType == CommandType.LIST) {
            return new ListCommand();
        } else if (commandType == CommandType.BYE) {
            return new ExitCommand();
        } else if (commandType == CommandType.DELETE
                || commandType == CommandType.DONE
                || commandType == CommandType.TODO
                || commandType == CommandType.DEADLINE
                || commandType == CommandType.EVENT
                || commandType == CommandType.FIND) { // Commands that have a description
            try {
                String description = commandElements[1];
                if (commandType == CommandType.DELETE) {
                    return new DeleteCommand(description);
                } else if (commandType == CommandType.DONE) {
                    return new DoneCommand(description);
                } else if (commandType == CommandType.FIND) {
                    return new FindCommand(description);
                } else {
                    return new AddCommand(commandType, description);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new NoDescriptionException(commandType.toString().toLowerCase());
            }
        } else {
            throw new NoCommandException();
        }
    }
}
