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
        if (input.matches(InputPattern.byePattern)) {
            return new ExitCommand(input);
        } else if (input.matches(InputPattern.completeTaskPattern)) {
            return new CompleteTaskCommand(input);
        } else if (input.matches(InputPattern.deleteTaskPattern)) {
            return new DeleteTaskCommand(input);
        } else if (input.matches(InputPattern.addEventPattern)) {
            return new AddEventCommand(input);
        } else if (input.matches(InputPattern.addDeadlinePattern)) {
            return new AddDeadlineCommand(input);
        } else if (input.matches(InputPattern.addTodoPattern)) {
            return new AddTodoCommand(input);
        } else if (input.matches(InputPattern.listPattern)) {
            return new ListCommand(input);
        } else {
            return new InvalidCommand(input);
        }
    }
}
