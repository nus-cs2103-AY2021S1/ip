package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.exceptions.EmptyTaskException;
import duke.exceptions.EmptyTimeException;
import duke.exceptions.WrongDateFormatException;


public class AddTaskParser extends Parser {
    private static boolean hasNoTime(String[] parseArray, int noOfSignalWordRequired, String signalWord) {
        return parseArray[1].split(signalWord).length == noOfSignalWordRequired;
    }

    /**
     * Parses info about a todo task and returns a command.
     *
     * @param parseArray An array of parsed information [description].
     * @return An addCommand.
     * @throws EmptyTaskException If the the task description is empty.
     */
    public static Command parseTodoCommand(String[] parseArray) throws EmptyTaskException {
        if (isOneWordCommand(parseArray)) {
            throw new EmptyTaskException("Please specify the todo. (´∀`)");
        } else {
            return new AddCommand(parseArray[0], parseArray[1], null);
        }
    }


    /**
     * Parses info about a deadline task and returns a command.
     *
     * @param parseArray An array of parsed information [description, date info].
     * @return An addCommand.
     * @throws EmptyTimeException If the the date is incomplete.
     * @throws WrongDateFormatException If the date is not in the format of yyyy-mm-dd.
     * @throws EmptyTaskException If the the task description is empty.
     */
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
            assert taskDescription.split(" /by ").length == 2;
            String name = taskDescription.split(" /by ")[0];
            String time = taskDescription.split(" /by ")[1];
            LocalDate date = LocalDate.parse(time);
            LocalDate[] dataArr = {date};
            return new AddCommand(type, name, dataArr);
        } catch (DateTimeParseException ex) {
            throw new WrongDateFormatException();
        }
    }

    /**
     * Parses info about an event task and returns a command.
     *
     * @param parseArray An array of parsed information [description, date info].
     * @return An addCommand.
     * @throws EmptyTimeException If the the date is incomplete.
     * @throws WrongDateFormatException If the date is not in the format of yyyy-mm-dd.
     * @throws EmptyTaskException If the the task description is empty.
     */
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

    /**
     * Parses info about a period-task and returns a command.
     *
     * @param parseArray An array of parsed information [description, date info].
     * @return An addCommand.
     * @throws EmptyTimeException If the the date is incomplete.
     * @throws WrongDateFormatException If the date is not in the format of yyyy-mm-dd.
     * @throws EmptyTaskException If the the task description is empty.
     */
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
