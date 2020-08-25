package duke.parser;

import duke.command.*;
import duke.task.Task;

public class Parser {

    public static Command parse(String userInput) {

        Task t = new Task(userInput);

        if (userInput.equals("list")) {
            return new ListCommand(userInput);
        } else if (t.getFirstWord().equals("done")) {
            return new DoneCommand(userInput);
        } else if (t.getFirstWord().equals("delete")) {
            return new DeleteCommand(userInput);
        } else if (userInput.equals("bye")) {
            return new ExitCommand(userInput);
        } else if (t.getFirstWord().equals("find")) {
            return new FindCommand(userInput);
        } else {
            return new AddCommand(userInput);
        }
    }
}

