package duke;

import duke.command.*;

public class Parser {

    // Parses user input and returns corresponding Command
    public static Command parse(String input) {
        // Tag refers to the command to perform
        String tag = input.split(" ")[0];

        switch (tag) {
            case "list":
                return new ListCommand();

            case "done":
                return new DoneCommand(input);

            case "delete":
                return new DeleteCommand(input);

            case "todo":
                return new AddTaskCommand("todo", input);
            case "event":
                return new AddTaskCommand("event", input);
            case "deadline":
                return new AddTaskCommand("deadline", input);

            case "find":
                return new FindCommand(input);

            case "bye":
                return new ByeCommand();

            default:
                return new UnknownCommand();
        }
    }

}
