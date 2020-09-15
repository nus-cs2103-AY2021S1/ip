package duke;

import java.util.Arrays;

/**
 * Encapsulates parsing of logic.
 */
public class Parser {
    /**
     * Returns appropriate Command given user input.
     * @param fullCommand User input.
     * @return Command of user input.
     * @throws DukeException If user inputs invalid command.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] strings = fullCommand.split(" ");
        String description = String.join(" ", Arrays.copyOfRange(strings, 1, strings.length));
        switch (convertCommandToEnum(strings[0])) {
        case LIST:
            if (strings.length > 1) {
                throw new DukeException("Please key in a correct command.");
            }
            return new ShowCommand();
        case DONE:
            return new UpdateCommand(description);
        case DELETE:
            return new DeleteCommand(description);
        case FIND:
            return new FindCommand(description);
        case TODO: case EVENT: case DEADLINE:
            return new AddCommand(convertCommandToEnum(strings[0]), description);
        case BYE:
            return new ExitCommand();
        case HELP:
            return new HelpCommand();
        default:
            throw new DukeException("Please key in a correct command.");
        }
    }

    private static CommandEnum convertCommandToEnum(String string) throws DukeException {
        switch (string) {
        case "bye":
            return CommandEnum.BYE;
        case "list":
            return CommandEnum.LIST;
        case "done":
            return CommandEnum.DONE;
        case "delete":
            return CommandEnum.DELETE;
        case "find":
            return CommandEnum.FIND;
        case "todo":
            return CommandEnum.TODO;
        case "deadline":
            return CommandEnum.DEADLINE;
        case "event":
            return CommandEnum.EVENT;
        case "help":
            return CommandEnum.HELP;
        default:
            throw new DukeException("Please key in a correct command.");
        }
    }
}
