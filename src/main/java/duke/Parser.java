package duke;

import duke.command.*;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Parses the user input and returns a corresponding command to be executed.
     *
     * @param input A string representing the user input.
     * @return A command to be executed.
     */
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
