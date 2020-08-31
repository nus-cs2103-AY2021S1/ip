package duke.parser;

import duke.command.Command;
import duke.command.CompleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.AddCommand;
import duke.command.FindCommand;

/**
 * Handles making sense of the user command.
 */
public class Parser {

    /**
     * Parses user input and returns an executable <code>Command</code> Object.
     *
     * @param str The raw user input.
     * @return An executable <code>Command</code> object.
     */
    public static Command parse(String str) {
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
        } else {
            return new AddCommand(str);
        }
    }

}
