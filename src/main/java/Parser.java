/**
 * deals with making sense of the user command
 */

public class Parser {
    public static Command parse(String fullCommand) {
        String firstWord = fullCommand.split(" ")[0];

        switch (firstWord) {
            case "list":
                return new ListCommand(fullCommand);
            case "done":
                return new DoneCommand(fullCommand);
            case "delete":
                return new DeleteCommand(fullCommand);
            case "todo":
                return new ToDoCommand(fullCommand);
            case "deadline":
                return new DeadlineCommand(fullCommand);
            case "event":
                return new EventCommand(fullCommand);
            case "find":
                return new FindCommand(fullCommand);
            case "bye":
                return new ExitCommand(fullCommand);
            default:
                return new InvalidCommand(fullCommand);
        }
    }
}
