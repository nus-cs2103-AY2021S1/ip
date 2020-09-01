package duke.command;

import duke.dukeexception.DukeException;
import duke.dukeexception.NoCommandException;
import duke.dukeexception.NoDescriptionException;

enum CommandType {
    LIST,
    DONE,
    DELETE,
    BYE,
    TODO,
    DEADLINE,
    EVENT,
    FIND
}

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
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
        } else if (commandType == CommandType.DELETE ||
                commandType == CommandType.DONE ||
                commandType == CommandType.TODO ||
                commandType == CommandType.DEADLINE ||
                commandType == CommandType.EVENT ||
                commandType == CommandType.FIND) { // Commands that have a description
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
            } catch (Exception e) {
                throw new NoDescriptionException(commandType.toString().toLowerCase());
            }
        } else {
            throw new NoCommandException();
        }
    }
}
