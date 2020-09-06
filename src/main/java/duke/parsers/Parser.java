package duke.parsers;

import duke.command.*;

/**
 * deals with making sense of the user command
 */
public class Parser {
    /**
     * takes the user's full input and returns the appropriate command that should be run based on the given input
     * @param fullCommand the entire input given by the user
     * @return the appropriate command based on the input given by the user
     */
    public static Command parse(String fullCommand) {
        assert fullCommand != null : "fullCommand should not be null";
        String firstWord = fullCommand.split(" ")[0];

        switch (firstWord) {
            case "list":
                return new ListCommand();
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
            case "clear":
                return new ClearCommand();
            case "help":
                return new HelpCommand();
            case "bye":
                return new ExitCommand();
            default:
                return new InvalidCommand();
        }
    }
}
