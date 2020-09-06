package duke.parsers;

import duke.command.AddTaskCommand;
import duke.command.ByeCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.ScheduleCommand;
import duke.command.UnknownCommand;

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
        assert input != null : "Input cannot be null!";

        // Tag refers to the command to perform
        String tag = input.split(" ")[0];

        switch (tag) {
        case "help":
            return new HelpCommand();

        case "list":
            return new ListCommand();

        case "done":
            return new DoneCommand(input);

        case "delete":
            return new DeleteCommand(input);

        case "clear":
            return new ClearCommand();

        case "todo":
            return new AddTaskCommand("todo", input);
        case "event":
            return new AddTaskCommand("event", input);
        case "deadline":
            return new AddTaskCommand("deadline", input);

        case "find":
            return new FindCommand(input);

        case "schedule":
            return new ScheduleCommand(input);

        case "bye":
            return new ByeCommand();

        default:
            return new UnknownCommand();
        }
    }

}
