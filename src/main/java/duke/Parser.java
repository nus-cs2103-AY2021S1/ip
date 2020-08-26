package duke;

import duke.command.*;

/**
 * Utility class to parse user's input and convert the input into executable commands
 */
public class Parser {

    /**
     * Parses user's input
     * @param inputCommand Input command from the user
     * @return An executable command
     * @throws DukeException If command provided is invalid
     */
    public static Command parse(String inputCommand) throws DukeException {
        String[] inputArr = inputCommand.split(" ", 2);
        String commandArg = inputArr.length == 2 ? inputArr[1] : null;
        
        CommandType op;
        try {
            op = CommandType.valueOf(inputArr[0].toUpperCase()); // type of operation
        } catch (IllegalArgumentException e) {
            op = CommandType.INVALID;
        }

        switch (op) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case TODO:
        case DEADLINE:
        case EVENT:
            return new AddCommand(op, commandArg);
        case DONE:
            return new DoneCommand(commandArg);
        case DELETE:
            return new DeleteCommand(commandArg);
        case FIND:
            return new FindCommand(commandArg);
        case INVALID: 
        default:    
            return new InvalidCommand();
        }
    }
}

