package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkDoneCommand;

public class Parser {
    /**
     * Parses user input and returns the correct command to execute
     *
     * @param input User input
     * @return Correct command for Duke to execute
     * @throws DukeException if exception encountered
     */
    private static boolean isMarkDoneCommand(String input) {
        return input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("done");
    }

    private static boolean isFindCommand(String input) {
        return input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("find");
    }

    private static boolean isDeleteCommand(String input) {
        return input.length() >= 6 && input.substring(0, 6).equalsIgnoreCase("delete");
    }

    private static boolean isAddTodoCommand(String input) {
        return input.length() >= 4 && input.substring(0, 4).equalsIgnoreCase("todo");
    }

    private static boolean isAddEventCommand(String input) {
        return input.length() >= 5 && input.substring(0, 5).equalsIgnoreCase("event");
    }

    private static boolean isAddDeadlineCommand(String input) {
        return input.length() >= 8 && input.substring(0, 8).equalsIgnoreCase("deadline");
    }

    public static Command parse(String input) throws DukeException {
        if (input.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (input.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (isMarkDoneCommand(input)) {
            int taskIndex = Integer.parseInt(input.substring(5)) - 1;
            return new MarkDoneCommand(taskIndex);
        } else if (isFindCommand(input)) {
            String search = input.substring(5);
            return new FindCommand(search);
        } else if (isDeleteCommand(input)) {
            int taskIndex = Integer.parseInt(input.substring(7)) - 1;
            return new DeleteCommand(taskIndex);
        } else if (isAddTodoCommand(input)) {
            return new AddCommand(input, ActionType.ADD_TODO);
        } else if (isAddEventCommand(input)) {
            return new AddCommand(input, ActionType.ADD_EVENT);
        } else if (isAddDeadlineCommand(input)) {
            return new AddCommand(input, ActionType.ADD_DEADLINE);
        } else {
            throw new DukeException("I have no idea what that means ¯\\_(ツ)_/¯");
        }
    }
}
