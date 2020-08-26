package duke;

import duke.commands.*;
import duke.patterns.InputPattern;

/**
 * Represents a parser.
 * Used to parse user input into commands.
 */
public class Parser {

    /**
     * Returns the corresponding command for a users text input.
     *
     * @param input the user text input
     * @return the command corresponding to the users input
     */
    public static Command parse(String input) {
        if (input.matches(InputPattern.BYE)) {
            return new ExitCommand(input);
        } else if (input.matches(InputPattern.COMPLETE_TASK)) {
            return new CompleteTaskCommand(input);
        } else if (input.matches(InputPattern.DELETE_TASK)) {
            return new DeleteTaskCommand(input);
        } else if (input.matches(InputPattern.ADD_EVENT)) {
            return new AddEventCommand(input);
        } else if (input.matches(InputPattern.ADD_DEADLINE)) {
            return new AddDeadlineCommand(input);
        } else if (input.matches(InputPattern.ADD_TODO)) {
            return new AddTodoCommand(input);
        } else if (input.matches(InputPattern.LIST)) {
            return new ListCommand(input);
        } else {
            return new InvalidCommand(input);
        }
    }
}
