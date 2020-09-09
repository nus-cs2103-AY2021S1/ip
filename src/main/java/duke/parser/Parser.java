package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.commands.*;
import duke.exceptions.*;
import duke.tasks.Todo;

public class Parser {
    public Parser() {
    }


    private static boolean isOneWordCommand(String[] parseArray) {
        return parseArray.length == 1;
    }

    private static Command parseListCommand(String[] parseArray) throws WrongDateFormatException {
        try {
            if (isOneWordCommand(parseArray)) {
                return new ListCommand(null);
            } else {
                String time = parseArray[1];
                LocalDate date = LocalDate.parse(time);
                return new ListCommand(date);
            }
        } catch(DateTimeParseException ex) {
            throw new WrongDateFormatException();
        }
    }

    private static int getArrayIndex(String[] parseArray) throws EmptyTaskException {
        try{
            String index = parseArray[1];
            return Integer.parseInt(index) - 1;
        } catch (DateTimeParseException ex) {
            throw new EmptyTaskException();
        }
    }

    private static Command parseDoneCommand(String[] parseArray) throws EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException();
        } else {
            int arrayIndex = getArrayIndex(parseArray);
            return new DoneCommand(arrayIndex);
        }
    }

    private static Command parseFindCommand(String[] parseArray) throws IncompleteMessageException {
        if (isOneWordCommand(parseArray)) {
            throw new IncompleteMessageException("Please specify keyword. (´∀`)");
        } else {
            String keyword = parseArray[1];
            return new FindCommand(keyword);
        }
    }

    private static Command parseDeleteCommand(String[] parseArray) throws EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException();
        } else {
            int arrayIndex = getArrayIndex(parseArray);
            return new DeleteCommand(arrayIndex);
        }
    }

    private static boolean hasNoTimeSignal(String[] parseArray) {
        return parseArray[1].split("/").length == 1;
    }

    private static boolean hasNoTime(String[] parseArray) {
        return parseArray[1].split("/")[1].split(" ", 2).length == 1;
    }

    private static Command parseTodoCommand(String[] parseArray) throws EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException("Please specify the todo. (´∀`)");
        } else {
            return new AddCommand(parseArray[0], parseArray[1], null);
        }
    }

    private static Command parseDeadlineCommand(String[] parseArray) throws EmptyTimeException,
            WrongDateFormatException, EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException("Please specify the deadline. (´∀`)");
        }

        if (hasNoTimeSignal(parseArray)) {
            throw new EmptyTimeException("Please specify deadline using \"/by\". (´∀`)");
        }
        if (hasNoTime(parseArray)) {
            throw new EmptyTimeException("Please don't leave the deadline blank~ (´∀`)");
        }

        try {
            String type = parseArray[0];
            String taskDescription = parseArray[1];
            String name = taskDescription.split(" /")[0];
            String time = taskDescription.split(" /")[1].split(" ", 2)[1];
            LocalDate date = LocalDate.parse(time);
            LocalDate[] dataArr = {date};
            return new AddCommand(type, name, dataArr);
        } catch (DateTimeParseException ex) {
            throw new WrongDateFormatException();
        }
    }

    private static Command parseEventCommand(String[] parseArray) throws EmptyTimeException,
            WrongDateFormatException, EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException("Please specify the event. (´∀`)");
        }
        if (hasNoTimeSignal(parseArray)) {
            throw new EmptyTimeException("Please specify event using \"/at\". (´∀`)");
        }
        if (hasNoTime(parseArray)) {
            throw new EmptyTimeException("Please don't leave the event time blank~ (´∀`)");
        }

        try {
            String type = parseArray[0];
            String taskDescription = parseArray[1];
            String name = taskDescription.split(" /")[0];
            String time = taskDescription.split(" /")[1].split(" ", 2)[1];
            LocalDate date = LocalDate.parse(time);
            LocalDate[] dataArr = {date};
            return new AddCommand(type, name, dataArr);
        } catch (DateTimeParseException ex) {
            throw new WrongDateFormatException();
        }
    }

    private static Command parsePeriodTaskCommand(String[] parseArray) throws EmptyTimeException,
            WrongDateFormatException, EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException();
        }
        if (hasNoTimeSignal(parseArray)) {
            throw new EmptyTimeException("Please specify start and end time using \"/from ... /to\". (´∀`)");
        }
        if (hasNoTime(parseArray)) {
            throw new EmptyTimeException("Please don't leave the time blank~ (´∀`)");
        }

        try {
            String type = parseArray[0];
            String taskDescription = parseArray[1];
            String name = taskDescription.split(" /")[0];
            String startString = taskDescription.split(" /")[1].split(" ", 2)[1];
            LocalDate start = LocalDate.parse(startString);
            String endString = taskDescription.split(" /")[2].split(" ", 2)[1];
            LocalDate end = LocalDate.parse(endString);
            LocalDate[] dataArr = {start, end};
            return new AddCommand(type, name, dataArr);
        } catch (DateTimeParseException ex) {
            throw new WrongDateFormatException();
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
            CommandNotFoundException, WrongDateFormatException, IncompleteMessageException {

        String[] parseArray = fullCommand.trim().split(" ", 2);
        String type = parseArray[0];

        switch(type) {
        case "bye":
            return new ExitCommand();
        case "list":
            return parseListCommand(parseArray);
        case "done":
            return parseDoneCommand(parseArray);
        case "find":
            return parseFindCommand(parseArray);
        case "delete":
            return parseDeleteCommand(parseArray);
        case "todo":
            return parseTodoCommand(parseArray);
        case "deadline":
            return parseDeadlineCommand(parseArray);
        case "event":
            return parseEventCommand(parseArray);
        case "period-task":
            return parsePeriodTaskCommand(parseArray);
        default:
            throw new CommandNotFoundException();
        }
    }
}
