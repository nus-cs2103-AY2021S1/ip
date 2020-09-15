package duke.parser;

import java.time.format.DateTimeParseException;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.exceptions.CommandNotFoundException;
import duke.exceptions.DateSequenceException;
import duke.exceptions.EmptyTaskException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.IncompleteMessageException;
import duke.exceptions.WrongDateFormatException;

public class Parser {
    public Parser() {
    }


    protected static boolean isOneWordCommand(String[] parseArray) {
        return parseArray.length == 1;
    }

    protected static int getArrayIndex(String[] parseArray) throws EmptyTaskException {
        try {
            String index = parseArray[1];
            return Integer.parseInt(index) - 1;
        } catch (DateTimeParseException ex) {
            throw new EmptyTaskException();
        }
    }

    /**
     * Cut an user-input String to smaller piece of information
     *
     * @param fullCommand String of user input.
     * @return Command A command object constructed with the info pieces.
     * @throws EmptyTaskException If no index is specified.
     * @throws EmptyTimeException If no date is specified.
     * @throws CommandNotFoundException If a unrecognisable command is used.
     * @throws WrongDateFormatException If the date cannot be parsed.
     */
    public static Command parse(String fullCommand) throws EmptyTaskException, EmptyTimeException,
            CommandNotFoundException, DateSequenceException, WrongDateFormatException, IncompleteMessageException {

        String[] parseArray = fullCommand.trim().split(" ", 2);
        String type = parseArray[0];

        switch(type) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListParser().parseListCommand(parseArray);
        case "done":
            return new DoneParser().parseDoneCommand(parseArray);
        case "find":
            return new FindParser().parseFindCommand(parseArray);
        case "delete":
            return new DeleteParser().parseDeleteCommand(parseArray);
        case "todo":
            return new AddTaskParser().parseTodoCommand(parseArray);
        case "deadline":
            return new AddTaskParser().parseDeadlineCommand(parseArray);
        case "event":
            return new AddTaskParser().parseEventCommand(parseArray);
        case "period-task":
            return new AddTaskParser().parsePeriodTaskCommand(parseArray);
        default:
            throw new CommandNotFoundException();
        }
    }
}
