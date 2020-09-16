package duke;

/**
 * Represents Parser class that parses input commands.
 */
public class Parser {

    /**
     * Returns the command of user input as an enumeration.
     * @param userInput User input as a String.
     * @return Command as enumeration.
     * @throws DukeException When an invalid input is keyed in.
     */
    public static CommandEnum getCommand(String userInput) throws DukeException {
        CommandEnum command;
        try {
            String[] input = userInput.split(" ");
            String extractCommand = input[0];
            command = CommandEnum.valueOf(extractCommand.toUpperCase());
        } catch (NullPointerException | IllegalArgumentException ex) {
            throw new DukeException(
                    "You have keyed in an invalid command or format!\n"
                            + "(Valid commands: todo, deadline, event, list,"
                            + " delete, bye, done, find, schedule)");
        }
        return command;
    }

    /**
     * Returns a specific command after parsing.
     *
     * @param userInput User input as a String.
     * @return Specific input command.
     * @throws DukeException When invalid command or formatting is entered.
     */
    public static Command parseCommands(String userInput) throws DukeException {
        CommandEnum command = getCommand(userInput);
        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand(userInput);
        case TODO:
            return new AddCommand(CommandEnum.TODO, userInput);
        case DEADLINE:
            return new AddCommand(CommandEnum.DEADLINE, userInput);
        case EVENT:
            return new AddCommand(CommandEnum.EVENT, userInput);
        case DELETE:
            return new DeleteCommand(userInput);
        case FIND:
            return new FindCommand(userInput);
        case SCHEDULE:
            return new ScheduleCommand(userInput);
        default:
            throw new DukeException(
                    "You have keyed in an invalid command!\n"
                            + "(Valid commands: todo, deadline,"
                            + " event, list, delete, bye, done, find, schedule)");
        }
    }
}
