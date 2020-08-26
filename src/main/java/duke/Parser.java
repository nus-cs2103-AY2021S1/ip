package duke;

import commands.ByeCommand;
import commands.Command;
import commands.DoneCommand;
import commands.ListCommand;
import commands.DeleteCommand;
import commands.AddCommand;
import commands.FindCommand;

import exceptions.InvalidCommandException;


/**
 * Class that initiates the Parser object. Reads command in string given by the user and returns
 * the appropriate Command object.
 */
public class Parser {
    private final static String BYE_COMMAND = "bye";
    private final static String LIST_COMMAND = "list";
    private final static String DONE_COMMAND = "done";
    private final static String TODO_COMMAND = "todo";
    private final static String DEADLINE_COMMAND = "deadline";
    private final static String EVENT_COMMAND = "event";
    private final static String DELETE_COMMAND = "delete";
    private final static String FIND_COMMAND = "find";

    /**
     * Reads command in string given by the user
     * Returns the appropriate Command object.
     * Throws InvalidCommandException if the command is unknown.
     *
     * @param fullCommand String which contains the command from the user.
     * @return Command object of the given command.
     * @throws InvalidCommandException If command is not recognisable.
     */
    public static Command parse(String fullCommand) throws InvalidCommandException {
        String[] tempArray = fullCommand.trim().split(" ");
        String command = tempArray[0];

        if (command.equals(BYE_COMMAND)) {
            return new ByeCommand(fullCommand);
        }
        if (command.equals(LIST_COMMAND)) {
            return new ListCommand(fullCommand);
        }
        if (command.equals(DONE_COMMAND)) {
            return new DoneCommand(fullCommand);
        }
        if (command.equals(DELETE_COMMAND)) {
            return new DeleteCommand(fullCommand);
        }
        if (command.equals(FIND_COMMAND)) {
            return new FindCommand(fullCommand);
        }
        if (isTask(command)) {
            return new AddCommand(fullCommand);
        }

        throw new InvalidCommandException();
    }

    public static boolean isTask(String command) {
        return isDeadlineCommand(command) || isToDoCommand(command) || isEventCommand(command);
    }

    public static boolean isDeadlineCommand(String command) {
        return command.equals(DEADLINE_COMMAND);
    }

    public static boolean isToDoCommand(String command) {
        return command.equals(TODO_COMMAND);
    }

    public static boolean isEventCommand(String command) {
        return command.equals(EVENT_COMMAND);
    }
}
