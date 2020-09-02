package duke.utils;

import duke.command.*;
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
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.length() >= 6 && command.substring(0, 5).equals("done ")) {
            int num = Integer.parseInt(command.split(" ")[1]);
            return new DoneCommand(num - 1);
        } else if (command.length() >= 8 && command.substring(0, 7).equals("delete ")) {
            int num = Integer.parseInt(command.split(" ")[1]);
            return new DeleteCommand(num - 1);
        } else {
            String[] parts = command.split(" ", 2);
            String taskType = parts[0];
            if (TaskType.isTask(taskType)) {
                if (parts.length < 2 || parts[1].isEmpty()) {
                    throw new DukeException(String.format("☹ OOPS!!! The description of %s cannot be empty.", parts[0]));
                } else {
                    return new AddCommand(parts[0],parts[1]);
                }
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
