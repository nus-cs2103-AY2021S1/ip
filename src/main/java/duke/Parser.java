package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.NoCommand;
import duke.task.TaskType;

/**
 * The Parser class will deal with making sense of the user command
 */
public class Parser {

    /**
     * Returns a boolean to determine if the String is empty after the
     * command word.
     *
     * @param input The String which represents the user input.
     * @param x The minimum length of the input for the details to be non-empty.
     * @return A boolean, true or false.
     */
    public static boolean hasEmptyDetails(String input, int x) {
        return input.length() <= x || input.substring(x).trim().isEmpty();
    }

    /**
     * Reads the input to determine which type of command Duke
     * should execute to reply the user.
     *
     * @param input The user input entered in the command line.
     * @return The Command to be executed.
     * @throws DukeException if the input is formatted wrongly.
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.trim().equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("done ") || input.equals("done")) {
            if (hasEmptyDetails(input, 5)) {
                throw new DukeException("Which task do you want to mark as done?");
            }
            String item = input.substring(5).trim();
            return new DoneCommand(item);
        } else if (input.startsWith("delete ") || input.equals("delete")) {
            if (hasEmptyDetails(input, 7)) {
                throw new DukeException("Which task do you want to delete?");
            }
            String item = input.substring(7).trim();
            return new DeleteCommand(item);
        } else if (input.startsWith("find ") || input.equals("find")) {
            if (hasEmptyDetails(input, 5)) {
                throw new DukeException("Which task do you want to find?");
            }
            String keyword = input.substring(5).trim();
            return new FindCommand(keyword);
        } else if (input.startsWith("todo ") || input.equals("todo")) {
            if (hasEmptyDetails(input, 5)) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            String name = input.substring(5).trim();
            return new AddCommand(TaskType.TODO, name, "");
        } else if (input.startsWith("deadline ") || input.equals("deadline")) {
            if (hasEmptyDetails(input, 9)) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String details = input.substring(9).trim();
            String[] split = details.split(" /by ");
            if (split.length != 2) {
                throw new DukeException("Please use the format: deadline (name) /by "
                        + "(yyyy-mm-dd)");
            }
            String name = split[0];
            String by = split[1];
            return new AddCommand(TaskType.DEADLINE, name, by);
        } else if (input.startsWith("event ") || input.equals("event")) {
            if (hasEmptyDetails(input, 6)) {
                throw new DukeException("The description of a event cannot be empty.");
            }
            String details = input.substring(6).trim();
            String[] split = details.split(" /at ");
            if (split.length != 2) {
                throw new DukeException("Please use the format: event (name) /at "
                        + "(yyyy-mm-dd)");
            }
            String name = split[0];
            String at = split[1];
            return new AddCommand(TaskType.EVENT, name, at);
        } else {
            return new NoCommand();
        }
    }
}
