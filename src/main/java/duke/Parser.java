package duke;

import duke.command.*;

public class Parser {

    /**
     * Returns a Command object based on the input keyed in to Duke by
     * the user.
     * 
     * @param fullCommand the command keyed in to Duke by the user.
     * @return Command object of the required type.
     */
    public static Command parse(String fullCommand) {
        String[] inputList = fullCommand.split(" ", 2);
        String argument = inputList.length > 1 ? inputList[1] : "";
        CommandType commandType;

        try {
            commandType = CommandType.valueOf(inputList[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.INVALID;
        }
        
        switch (commandType) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DATE:
            return new DateCommand(argument);
        case DONE:
            return new DoneCommand(Integer.parseInt(inputList[1]));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(inputList[1]));
        case TODO:
            return new ToDoCommand(argument);
        case EVENT:
            return new EventCommand(argument);
        case DEADLINE:
            return new DeadlineCommand(argument);
        case INVALID: default:
            return new InvalidCommand();
        }
        
    }
    
}
