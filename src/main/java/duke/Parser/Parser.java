package duke.Parser;

import duke.Command.Command;
import duke.Command.CompleteCommand;
import duke.Command.ExitCommand;
import duke.Command.ListCommand;
import duke.Command.DeleteCommand;
import duke.Command.AddCommand;

public class Parser {

    /**
     * Parses user input and returns an executable <code>Command</code> Object
     *
     * @param str The raw user input
     * @return An executable <code>Command</code> object
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
        } else {
            return new AddCommand(str);
        }
    }

}
