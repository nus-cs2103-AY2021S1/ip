package duke.utils;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exceptions.DukeException;
import duke.task.TaskType;

/**
 * A helper class that contains method dealing with command parsing.
 * such as parsing a string to a command.
 */
public class Parser {

    /**
     * Parse the command in string to the correct Command Object.
     *
     * @param command the string that wants to be parsed.
     * @return a Command which can be either ExitCommd, ListCommand, DoneCommand,
     * DeleteCommand or a AddCommand.
     * @throws DukeException  If the input string is not in the correct format.
     */
    public static Command parse(String command) throws DukeException {
        String[] parts = command.split(" ", 2);
        String keyword = parts[0];
        if (keyword.equals("bye")) {
            return new ExitCommand();
        } else if (keyword.equals("list")) {
            return new ListCommand();
        } else if (keyword.equals("help")) {
            return new HelpCommand();
        } else if (keyword.equals("done")) {
            int num = Integer.parseInt(command.split(" ")[1]);
            return new DoneCommand(num - 1);
        } else if (keyword.equals("delete")) {
            int num = Integer.parseInt(command.split(" ")[1]);
            return new DeleteCommand(num - 1);
        } else if (keyword.equals("find")) {
            return new FindCommand(command.split(" ")[1]);
        } else if (TaskType.isTask(keyword)) {
            if (parts.length < 2 || parts[1].isEmpty()) {
                throw new DukeException(String.format("OOPS!!! The description of %s cannot be empty.", parts[0]));
            } else {
                return new AddCommand(parts[0], parts[1]);
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
