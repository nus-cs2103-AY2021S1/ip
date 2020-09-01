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
<<<<<<< HEAD
     * Generating a Command object based on user input.
=======
     * Generating a Command object based on user input
>>>>>>> branch-Level-10
     * @param command User input as String
     * @param list Current list of tasks
     * @param storage Access to duke.ser
     * @return The appropriate Command object to execute
     */
    public static Command parse(String command, TaskList list, Storage storage) {
        Command c;
        if (command.equals("bye")) {
            c = new ByeCommand(command, list, storage);
        } else if (command.equals("list")) {
            c = new ListCommand(command, list, storage);
        } else if (command.startsWith("delete")) {
            if (command.equals("delete")) {
                c = new InvalidCommand(command, list, storage);
            } else {
                c = new DeleteCommand(command, list, storage);
            }
        } else if (command.startsWith("done")) {
            if (command.equals("done")) {
                c = new InvalidCommand(command, list, storage);
            } else {
                c = new DoneCommand(command, list, storage);
            }
        } else if (command.startsWith("todo")) {
            if (command.equals("todo")) {
                c = new InvalidCommand(command, list, storage);
            } else {
                c = new TodoCommand(command, list, storage);
            }
        } else if (command.startsWith("deadline")) {
            if (command.equals("deadline")) {
                c = new InvalidCommand(command, list, storage);
            } else {
                c = new DeadlineCommand(command, list, storage);
            }
        } else if (command.startsWith("event")) {
            if (command.equals("event")) {
                c = new InvalidCommand(command, list, storage);
            } else {
                c = new EventCommand(command, list, storage);
            }
        } else if (command.startsWith("find")) {
            if (command.equals("find")) {
                c = new InvalidCommand(command, list, storage);
            } else {
                c = new FindCommand(command, list, storage);
            }
        } else {
            c = new InvalidCommand(command, list, storage);
        }
        return c;
    }
}
