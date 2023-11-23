/**
 * Represents the system used to parse user commands.
 */
public class Parser {
    /**
     * Returns a specific Command to tackle and parse user input.
     * @param input user's command.
     * @return Specific Command.
     * @throws DukeException If an invalid command is provided.
     */
    // main driver function for duke to tackle and parse commands
    public static Command parse(String input) throws DukeException {
        assert input != null : "There should be an input!";

        String[] splittedWords = input.split("\\s", 2); // splits string based on whitespace
        String command = splittedWords[0];
        String afterCommand = null;
        if (splittedWords.length > 1) {
            afterCommand = splittedWords[1];
        }

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException ex) {
            commandType = CommandType.UNIDENTIFIED;
        }

        // handle all different commands using switch and enum instead; organised the methods
        // to make the code look neater
        switch (commandType) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand(afterCommand);
        case TODO:
            return new ToDoCommand(afterCommand);
        case DEADLINE:
            return new DeadlineCommand(afterCommand);
        case EVENT:
            return new EventCommand(afterCommand);
        case DELETE:
            return new DeleteCommand(afterCommand);
        case FIND:
            return new FindCommand(afterCommand);
        case UNDO:
            return new UndoCommand(afterCommand);
        case HELP:
            return new HelpCommand();
        case UNIDENTIFIED:
            // if a bad command is thrown at Duke
            throw new DukeException("Please enter a command I understand!");
        default:
            // should not exist
            throw new DukeException("Invalid!");
        }
    }
}
