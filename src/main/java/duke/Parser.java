package duke;

import duke.command.*;

public class Parser {
    
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
        case INVALID: 
        default:    
            return new InvalidCommand();
        }
    }
}

