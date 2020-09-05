package duke.parser;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeArgumentException;

/**
 * Utility class for reading input and files
 */
public final class CommandParser {

    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String FIND = "find";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String EVENT = "event";
    private static final String DEADLINE = "deadline";

    /**
     * Converts input text into the appropriate command.
     *
     * @param text the input text from the user
     * @return the Command to be executed
     * @throws DukeArgumentException if the input text did not match any existing Command types.
     */
    public static Command parseCommand(String text) throws DukeArgumentException {
        String[] parsedInput = text.split(" ", 2);
        try {
            switch (parsedInput[0].toLowerCase()) {
            case BYE:
                return new ExitCommand();
            case LIST:
                return new ListCommand();
            case FIND:
                return new FindCommand(parsedInput[1]);
            case DONE:
                return new DoneCommand(parsedInput[1]);
            case DELETE:
                return new DeleteCommand(parsedInput[1]);
            case TODO:
                return new TodoCommand(parsedInput[1]);
            case EVENT:
                return new EventCommand(parsedInput[1]);
            case DEADLINE:
                return new DeadlineCommand(parsedInput[1]);
            default:
                throw new DukeArgumentException("Command did not match any known commands.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeArgumentException("Insufficient arguments for the command.");
        }
    }


}
