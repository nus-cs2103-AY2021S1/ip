package duke.Parser;

import duke.Command.*;

import duke.Exception.DukeException;

public class Parser {

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
