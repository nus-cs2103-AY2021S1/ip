package duke;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindByDateCommand;
import duke.command.FindByKeywordCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TagCommand;
import duke.command.ToDoCommand;
import duke.exception.InvalidFunctionException;

/**
 * Represents a parser to make sense of the user inputs so that
 * the correct command can be executed by DukeBot.
 */
public class Parser {

    private static final String LIST_COMMAND = "list";
    private static final String EXIT_COMMAND = "bye";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DONE_COMMAND = "done";
    private static final String DELETE_COMMAND = "delete";
    private static final String FIND_BY_DATE_COMMAND = "find_by_date";
    private static final String FIND_BY_KEYWORD_COMMAND = "find";
    private static final String HELP_COMMAND = "help";
    private static final String TAG_COMMAND = "tag";

    /**
     * Parses the input entered by users and returns a Command object to be
     * executed by DukeBot if the input is valid.
     *
     * @param userInput Inputs entered by the user.
     * @return Command object.
     * @throws InvalidFunctionException If the input is not a valid command.
     */
    public static Command parse(String userInput) throws InvalidFunctionException {
        String message = userInput.trim();
        String[] parsedCommand = message.split(" ", 2);
        String function = parsedCommand[0];
        if (message.isEmpty()) {
            String err = "No input was entered! Please enter something!";
            throw new InvalidFunctionException(err);
        } else if (message.equals(Parser.HELP_COMMAND)) {
            return new HelpCommand();
        } else if (message.equals(Parser.EXIT_COMMAND)) {
            return new ExitCommand();
        } else if (message.equals(Parser.LIST_COMMAND)) {
            return new ListCommand();
        } else if (function.equals(Parser.DONE_COMMAND)) {
            return new DoneCommand(parsedCommand);
        } else if (function.equals(Parser.TODO_COMMAND)) {
            parsedCommand = message.split("todo");
            return new ToDoCommand(parsedCommand);
        } else if (function.equals(Parser.DEADLINE_COMMAND)) {
            parsedCommand = message.split("deadline");
            return new DeadlineCommand(parsedCommand);
        } else if (function.equals(Parser.EVENT_COMMAND)) {
            parsedCommand = message.split("event");
            return new EventCommand(parsedCommand);
        } else if (function.equals(Parser.DELETE_COMMAND)) {
            return new DeleteCommand(parsedCommand);
        } else if (function.equals(Parser.FIND_BY_DATE_COMMAND)) {
            return new FindByDateCommand(parsedCommand);
        } else if (function.equals(Parser.FIND_BY_KEYWORD_COMMAND)) {
            return new FindByKeywordCommand(parsedCommand);
        } else if (function.equals(Parser.TAG_COMMAND)) {
            return new TagCommand(parsedCommand);
        } else {
            return new InvalidCommand();
        }
    }
}
