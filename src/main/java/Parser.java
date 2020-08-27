/**
 * Class used for parsing input from user command.
 */
public class Parser {

    /**
     * Parses input, and calls corresponding functions.
     *
     * @param input String input from user.
     * @return Corresponding Command with respect to input.
     * @throws DukeException  When date is in wrong format, or task lacks description.
     */
    public static Command parse(String input) throws DukeException {
        String second;
        int index;

        String[] parts = input.split(" ", 2);
        String action = parts[0].toUpperCase();

        CommandName commandName = tryEnum(action);
        switch(commandName) {
        case LIST:
            return new ListCommand();
        case DONE:
            second = parts[1];
            index = Integer.parseInt(second);
            return new DoneCommand(index);
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            return handleTask(parts, commandName);
        case DELETE:
            second = parts[1];
            index = Integer.parseInt(second);
            return new DeleteCommand(index);
        case FIND:
            second = parts[1];
            return new FindCommand(second);
        case BYE:
            // Fallthrough
        default:
            return new ExitCommand();
        }
    }

    /**
     * Takes in task info and command and calls respective handler (e.g. handle event).
     *
     * @param inputs Task info, after removing first word from user input.
     * @param commandName CommandName (e.g. Event), first word from user input.
     * @throws DukeException If deadline date not input for deadline, or event date not input for event.
     */
    private static Command handleTask(String[] inputs, CommandName commandName) throws DukeException {
        try {
            String taskInfo = inputs[1];
            String[] taskInfoParts;
            String description;
            String date;
            //TODO: in switch/if-else statement, okay to define early if we use the same variable name?

            switch (commandName) {
            case TODO:
                return new AddCommand(commandName, taskInfo);
            case DEADLINE:
                taskInfoParts = taskInfo.split(" /by ", 2);
                description = taskInfoParts[0];
                date = taskInfoParts[1];
                return new AddCommand(commandName, description, date);
            case EVENT:
                taskInfoParts = taskInfo.split(" /at ", 2);
                description = taskInfoParts[0];
                date = taskInfoParts[1];
                return new AddCommand(commandName, description, date);
            default:
                return new AddCommand(commandName, "");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            String error = "     OOPS!!! The description of a " + commandName.toString().toLowerCase()
                + " cannot be empty.";
            throw new DukeException(error);
        }
    }

    private static CommandName tryEnum(String action) throws DukeException {
        try {
            return CommandName.valueOf(action);
        } catch (IllegalArgumentException e) {
            throw new DukeException("     OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
