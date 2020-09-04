package duke.parser;

import duke.command.Command;
import duke.command.CompleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.AddCommand;
import duke.command.FindCommand;
import duke.exception.DukeException;

/**
 * Handles making sense of the user command.
 */
public class Parser {

    /**
     * Parses user input and returns an executable <code>Command</code> Object.
     *
     * @param str The raw user input.
     * @return An executable <code>Command</code> object.
     * @throws DukeException If an invalid command is passed in.
     */
    public static Command parse(String str) throws DukeException {
        if (str.equals("bye")) {
            return new ExitCommand();
        } else if (str.equals("list")) {
            return new ListCommand();
        } else if (str.contains("done")) {
            int taskIndex = Integer.parseInt(str.split("\\s+")[1]);
            return new CompleteCommand(taskIndex);
        } else if (str.contains("delete")) {
            int taskIndex = Integer.parseInt(str.split("\\s+")[1]);
            return new DeleteCommand(taskIndex);
        } else if (str.contains("find")) {
            String keyword = str.substring(5).trim();
            return new FindCommand(keyword);
        } else if (str.matches("^(todo|deadline|event).*")) {
            return new AddCommand(str);
        } else {
            throw new DukeException("did you type the wrong command? Try again!");
        }
    }
}
