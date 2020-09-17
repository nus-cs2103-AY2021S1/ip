package duke.parser;

import duke.command.ByeCommand;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.command.UserCommand;

/**
 * Parses user's input.
 */
public class Parser {

    /**
     * @param userInput User's input.
     * @return command Corresponding Command based on user's input.
     */
    public static UserCommand parse(String userInput) {
        String firstArg = userInput.trim().split(" ")[0];
        switch (userInput) {
        case "bye":
            return new ByeCommand(userInput);
        case "list":
            return new ListCommand(userInput);
        default:
            switch (firstArg) {
            case "done":
                return new DoneCommand(userInput);
            case "delete":
                return new DeleteCommand(userInput);
            case "todo":
                return new ToDoCommand(userInput);
            case "deadline":
                return new DeadlineCommand(userInput);
            case "event":
                return new EventCommand(userInput);
            case "find":
                return new FindCommand(userInput);
            default:
                return new InvalidCommand(userInput);
            }
        }
    }
}
