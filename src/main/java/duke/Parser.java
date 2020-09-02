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
     * @param command User input as String
     * @param list Current list of tasks
     * @param storage Access to duke.txt
     * @return The appropriate Command object to execute
     */
    public static Command parse(String command, TaskList list, Storage storage) {
        Command parsedCommand;
        if (command.equals("bye")) {
            parsedCommand = new ByeCommand(command, list, storage);
        } else if (command.equals("list")) {
            parsedCommand = new ListCommand(command, list, storage);
        } else if (command.startsWith("delete")) {
            if (command.equals("delete")) {
                parsedCommand = new InvalidCommand(command, list, storage);
            } else {
                parsedCommand = new DeleteCommand(command, list, storage);
            }
        } else if (command.startsWith("done")) {
            if (command.equals("done")) {
                parsedCommand = new InvalidCommand(command, list, storage);
            } else {
                parsedCommand = new DoneCommand(command, list, storage);
            }
        } else if (command.startsWith("todo")) {
            if (command.equals("todo")) {
                parsedCommand = new InvalidCommand(command, list, storage);
            } else {
                parsedCommand = new TodoCommand(command, list, storage);
            }
        } else if (command.startsWith("deadline")) {
            if (command.equals("deadline")) {
                parsedCommand = new InvalidCommand(command, list, storage);
            } else {
                parsedCommand = new DeadlineCommand(command, list, storage);
            }
        } else if (command.startsWith("event")) {
            if (command.equals("event")) {
                parsedCommand = new InvalidCommand(command, list, storage);
            } else {
                parsedCommand = new EventCommand(command, list, storage);
            }
        } else if (command.startsWith("find")) {
            if (command.equals("find")) {
                parsedCommand = new InvalidCommand(command, list, storage);
            } else {
                parsedCommand = new FindCommand(command, list, storage);
            }
        } else {
            parsedCommand = new InvalidCommand(command, list, storage);
        }
        return parsedCommand;
    }
}
