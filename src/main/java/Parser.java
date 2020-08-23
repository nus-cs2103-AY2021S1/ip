public class Parser {

    // main driver function for duke to tackle and parse commands
    public static Command parse(String input) throws DukeException {
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
        case UNIDENTIFIED:
            // if a bad command is thrown at Duke
            throw new DukeException("Please enter a command I understand!");
        }
        return null;
    }
}
