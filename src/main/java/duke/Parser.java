package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.InvalidCommandException;

/**
 * Responsible for the parsing of the user's inputs.
 */
public class Parser {
    /**
     * Parses an input from the user.
     *
     * @param command the input to be parsed
     * @return a <code>Command</code> that can be executed
     * @throws InvalidCommandException if the given input is not supported by Duke
     */
    public static Command parse(String command) throws InvalidCommandException {
        command = command.trim();
        String[] inputArray = command.split("\\s+");
        switch (inputArray[0]) {
        case "bye":
            return new ByeCommand();
        case "":
            return null;
        case "todo":
            return new ToDoCommand(command);
        case "deadline":
            return new DeadlineCommand(command);
        case "event":
            return new EventCommand(command);
        case "done":
            return new DoneCommand(inputArray);
        case "delete":
            return new DeleteCommand(inputArray);
        case "list":
            return new ListCommand(inputArray);
        case "find":
            return new FindCommand(command);
        default:
            throw new InvalidCommandException("I'm not sure what you're talking about.");
        }
    }
}
