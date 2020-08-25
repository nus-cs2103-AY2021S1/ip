package duke.parser;

import duke.command.AddCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.Command;
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
        } else {
            return new AddCommand(userInput);
        }
    }
}

