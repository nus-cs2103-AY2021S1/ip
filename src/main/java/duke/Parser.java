package duke;

public class Parser {

    /**
     * Returns the command object parsed from user input.
     * @param input User input.
     * @return Command parsed from use input.
     * @throws DukeException If input command type is incorrect.
     */
    public static Command parse(String input) throws DukeException {
        String[] inputs = input.split("\\s", 2);
        CommandType commandType = convertInputToEnum(inputs[0].toLowerCase());
        return new Command(commandType, inputs);
    }

    /**
     * Converts user input command to CommandType Enum.
     * @param input User input of command.
     * @return CommandType Enum.
     * @throws DukeException
     */
    private static CommandType convertInputToEnum(String input) throws DukeException {
        switch (input) {
        case "bye":
            return CommandType.BYE;
        case "list":
            return CommandType.LIST;
        case "done":
            return CommandType.DONE;
        case "delete":
            return CommandType.DELETE;
        case "todo":
            return CommandType.TODO;
        case "deadline":
            return CommandType.DEADLINE;
        case "event":
            return CommandType.EVENT;
        case "help":
            return CommandType.HELP;
        default:
            throw new DukeException("OOPS! I'm sorry, but I don't know what that means :-(");
        }
    }
}
