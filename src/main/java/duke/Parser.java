package duke;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddTodoCommand;
import duke.commands.Command;
import duke.commands.CompleteTaskCommand;
import duke.commands.DeleteTaskCommand;
import duke.commands.ExitCommand;
import duke.commands.FindAllContainingCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.patterns.InputPattern;

/**
 * Represents a parser.
 * Used to parse user input into commands.
 */
public class Parser {

    /**
     * Returns the corresponding command for a users text input.
     *
     * @param input User text input.
     * @return Command corresponding to the users input.
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
        } else if (input.matches(InputPattern.FIND_ALL_CONTAINING)) {
            return new FindAllContainingCommand(input);
        } else {
            return new InvalidCommand(input);
        }
    }
}
