package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/**
 * Parser class handles any incoming user input and generates the appropriate command.
 */
public class Parser {
    /**
     * @param fullCommand User input as String
     * @param list Current list of tasks
     * @param storage Access to duke.txt
     * @return The appropriate Command object to execute
     */
    public static Command parse(String fullCommand, TaskList list, Storage storage) {
        Command parsedCommand;
        String command = fullCommand.split("\\s+")[0];
        switch (command) {
        case "bye":
            parsedCommand = new ByeCommand(fullCommand, list, storage);
            break;
        case "list":
            parsedCommand = new ListCommand(fullCommand, list, storage);
            break;
        case "delete":
            parsedCommand = new DeleteCommand(fullCommand, list, storage);
            break;
        case "done":
            parsedCommand = new DoneCommand(fullCommand, list, storage);
            break;
        case "todo":
            parsedCommand = new TodoCommand(fullCommand, list, storage);
            break;
        case "deadline":
            parsedCommand = new DeadlineCommand(fullCommand, list, storage);
            break;
        case "event":
            parsedCommand = new EventCommand(fullCommand, list, storage);
            break;
        case "find":
            parsedCommand = new FindCommand(fullCommand, list, storage);
            break;
        default:
            parsedCommand = new InvalidCommand(fullCommand, list, storage);
            break;
        }
        return parsedCommand;
    }
}
