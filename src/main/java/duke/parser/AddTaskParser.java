package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.exceptions.EmptyTaskException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.WrongDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class AddTaskParser extends Parser {
    private static boolean hasNoTime(String[] parseArray, int noOfSignalWordRequired, String signalWord) {
        return parseArray[1].split(signalWord).length == noOfSignalWordRequired;
    }

    public static Command parseTodoCommand(String[] parseArray) throws EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException("Please specify the todo. (´∀`)");
        } else {
            return new AddCommand(parseArray[0], parseArray[1], null);
        }
    }

    public static Command parseDeadlineCommand(String[] parseArray) throws EmptyTimeException,
            WrongDateFormatException, EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException("Please specify the deadline. (´∀`)");
        }

        if (hasNoTime(parseArray, 1, " /by ")) {
            throw new EmptyTimeException("Please specify deadline using \"/by yyyy-mm-dd\". (´∀`)");
        }

        try {
            String type = parseArray[0];
            String taskDescription = parseArray[1];
            assert taskDescription.split(" /at ").length == 2;
            String name = taskDescription.split(" /by ")[0];
            String time = taskDescription.split(" /by ")[1];
            LocalDate date = LocalDate.parse(time);
            LocalDate[] dataArr = {date};
            return new AddCommand(type, name, dataArr);
        } catch (DateTimeParseException ex) {
            throw new WrongDateFormatException();
        }
    }


    public static Command parseEventCommand(String[] parseArray) throws EmptyTimeException,
            WrongDateFormatException, EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException("Please specify the event. (´∀`)");
        }

        if (hasNoTime(parseArray, 1, " /at ")) {
            throw new EmptyTimeException("Please specify event using \"/at yyyy-mm-dd\". (´∀`)");
        }

        try {
            String type = parseArray[0];
            String taskDescription = parseArray[1];
            assert taskDescription.split(" /at ").length == 2;
            String name = taskDescription.split(" /at ")[0];
            String time = taskDescription.split(" /at ")[1];
            LocalDate date = LocalDate.parse(time);
            LocalDate[] dataArr = {date};
            return new AddCommand(type, name, dataArr);
        } catch (DateTimeParseException ex) {
            throw new WrongDateFormatException();
        }
    }

    public static Command parsePeriodTaskCommand(String[] parseArray) throws EmptyTimeException,
            WrongDateFormatException, EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException("Please don't leave the description blank~ (´∀`)");
        }

        if (hasNoTime(parseArray, 2, " /from | /to ")) {
            throw new EmptyTimeException("Please specify start and end time using \"/from yyyy-mm-dd "
                    + "/to yyyy-mm-dd\". (´∀`)");
        }

        try {
            String type = parseArray[0];
            String taskDescription = parseArray[1];
            assert taskDescription.split(" /from | /to ").length == 3;
            String name = taskDescription.split(" /from ")[0];
            String startString = taskDescription.split(" /from | /to ")[1];
            LocalDate start = LocalDate.parse(startString);
            String endString = taskDescription.split(" /from | /to ")[2];
            LocalDate end = LocalDate.parse(endString);
            LocalDate[] dataArr = {start, end};
            return new AddCommand(type, name, dataArr);
        } catch (DateTimeParseException ex) {
            throw new WrongDateFormatException();
        }
    }
}
