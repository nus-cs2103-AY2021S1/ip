import java.util.HashSet;

/**
 * Parses user input.
 */
public class Parser {
    /**
     * Parses user input and passes the input to command for execution.
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws DukeException if the command is not recognised or the description of the command is empty
     */
    public static Command parseCommand(String userInput)
            throws DukeException {
        String[] input = userInput.split(" ", 2);
        String commandWord = input[0];
        String arguments = "";

        HashSet<String> commandsWithArgs = new HashSet<>();

        commandsWithArgs.add(DeadlineCommand.COMMAND_WORD);
        commandsWithArgs.add(DeleteCommand.COMMAND_WORD);
        commandsWithArgs.add(DoneCommand.COMMAND_WORD);
        commandsWithArgs.add(EventCommand.COMMAND_WORD);
        commandsWithArgs.add(ToDoCommand.COMMAND_WORD);
        commandsWithArgs.add(FindCommand.COMMAND_WORD);

        if (commandsWithArgs.contains(commandWord)) {
            if (input.length > 1) {
                arguments = input[1];
            } else {
                String message = String.format("OOPS! "
                        + "The description of a %s cannot be empty.", commandWord);
                throw new DukeException(message);
            }
        }

        switch (commandWord) {
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommand(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommand(arguments);
        case DoneCommand.COMMAND_WORD:
            return new DoneCommand(arguments);
        case EventCommand.COMMAND_WORD:
            return new EventCommand(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommand(arguments);
        case ToDoCommand.COMMAND_WORD:
            return new ToDoCommand(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(arguments);
        default:
            throw new DukeException("OOPS! I'm sorry, but I don't know what that means.");
        }
    }
}
