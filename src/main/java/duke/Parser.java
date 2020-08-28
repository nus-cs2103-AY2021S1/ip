package duke;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.exceptions.InvalidCommandException;


/**
 * Class that initiates the Parser object. Reads command in string given by the user and returns
 * the appropriate Command object.
 */
public class Parser {
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_COMMAND = "find";

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
