package seedu.duke;

import seedu.duke.command.ByeCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeadlineCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.EventCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.ToDoCommand;

/**
 * Responsible for the parsing of the user's inputs.
 */
public class Parser {
    /**
     * Parses an input from the user.
     *
     * @param command the input to be parsed
     * @return a <code>Command</code> that can be executed
     * @throws DukeException if the given input is not supported by Duke
     */
    public static Command parse(String command) throws DukeException {
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
            throw new DukeException("I'm not sure what you're talking about.");
        }
    }
}
