package duke;

import java.util.Arrays;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException{
        String[] strings = fullCommand.split(" ");
        String description = String.join(" ", Arrays.copyOfRange(strings, 1, strings.length));
        switch (convertToEnum(strings[0])) {
        case LIST:
            if (strings.length > 1) throw new DukeException("Please key in a correct command.");
            return new ShowCommand();
        case DONE:
            return new UpdateCommand(description);
        case DELETE:
            return new DeleteCommand(description);
        case TODO: case EVENT: case DEADLINE:
            return new AddCommand(convertToEnum(strings[0]), description);
        case BYE:
            return new ExitCommand();
        default:
            throw new DukeException("Please key in a correct command.");
        }
    }

    private static CommandEnum convertToEnum(String string) throws DukeException{
        switch (string) {
        case "bye":
            return CommandEnum.BYE;
        case "list":
            return CommandEnum.LIST;
        case "done":
            return CommandEnum.DONE;
        case "delete":
            return CommandEnum.DELETE;
        case "todo":
            return CommandEnum.TODO;
        case "deadline":
            return CommandEnum.DEADLINE;
        case "event":
            return CommandEnum.EVENT;
        default:
            throw new DukeException("Please key in a correct command.");
        }
    }
}
