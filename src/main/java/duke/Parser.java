package duke;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.TodoCommand;
import exception.DukeException;
import exception.EmptyDescriptionException;
import exception.InvalidDateException;
import exception.InvalidInputException;


/**
 * Class that Parses the user input given to the chatbot
 */
public class Parser {

    private static Pattern todo = Pattern.compile("todo (.+)");
    private static Pattern deadline = Pattern.compile("deadline (.+?) /by (.+)");
    private static Pattern event = Pattern.compile("event (.+?) /at (.+)");
    private static Pattern done = Pattern.compile("done ([0-9]+)");
    private static Pattern delete = Pattern.compile("delete ([0-9]+)");
    private static Pattern datePattern =
            Pattern.compile("^([0-9]{1,2})[/-]([0-9]{1,2})[/-]([0-9]{4}) ([0-9]{4})$");
    private static Pattern find = Pattern.compile("find (.+)");

    /**
     * Static method that takes in a String and returns a Command
     *
     * @param str String to be parsed
     * @return Object of the Command Class.
     * @throws DukeException Any exceptions with parsing.
     */
    public static Command parse(String str) throws DukeException {
        switch (str.split(" ")[0]) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            return parseDoneCommand(str);
        case "delete":
            return parseDeleteCommand(str);
        case "todo":
            return parseTodoCommand(str);
        case "deadline":
            return parseDeadlineCommand(str);
        case "event":
            return parseEventCommand(str);
        case "find":
            return parseFindCommand(str);
        default:
            throw new InvalidInputException();
        }
    }

    /**
     * Parses the string str into a Find Command
     *
     * @param str String to be parsed
     * @return Object of the Find Command Class.
     * @throws EmptyDescriptionException Exception thrown if str has no arguments.
     */
    private static Command parseFindCommand(String str) throws EmptyDescriptionException {
        Matcher findMatcher = find.matcher(str);
        if (findMatcher.find()) {
            return new FindCommand(findMatcher.group(1));
        } else {
            throw new EmptyDescriptionException("find");
        }
    }

    /**
     * Parses the string str into a Event Command
     *
     * @param str String to be parsed
     * @return Object of the Event Command Class.
     * @throws EmptyDescriptionException Exception thrown if str has no arguments.
     */
    private static Command parseEventCommand(String str) throws InvalidDateException, EmptyDescriptionException {
        Matcher eventMatcher = event.matcher(str);
        if (eventMatcher.find()) {
            LocalDateTime timing = Parser.parseDate(eventMatcher.group(2));
            return new EventCommand(eventMatcher.group(1), timing);
        } else {
            throw new EmptyDescriptionException("event");
        }
    }

    /**
     * Parses the string str into a Deadline Command
     *
     * @param str String to be parsed
     * @return Object of the Deadline Command Class.
     * @throws EmptyDescriptionException Exception thrown if str has no arguments.
     */
    private static Command parseDeadlineCommand(String str) throws InvalidDateException, EmptyDescriptionException {
        Matcher deadlineMatcher = deadline.matcher(str);
        if (deadlineMatcher.find()) {
            LocalDateTime deadLine = Parser.parseDate(deadlineMatcher.group(2));
            return new DeadlineCommand(deadlineMatcher.group(1), deadLine);
        } else {
            throw new EmptyDescriptionException("deadline");
        }
    }

    /**
     * Parses the string str into a Todo Command
     *
     * @param str String to be parsed
     * @return Object of the Todo Command Class.
     * @throws EmptyDescriptionException Exception thrown if str has no arguments.
     */
    private static Command parseTodoCommand(String str) throws EmptyDescriptionException {
        Matcher todoMatcher = todo.matcher(str);
        if (todoMatcher.find()) {
            String todo = todoMatcher.group(1);
            return new TodoCommand(todo);
        } else {
            throw new EmptyDescriptionException("todo");
        }
    }

    /**
     * Parses the string str into a Delete Command
     *
     * @param str String to be parsed
     * @return Object of the Delete Command Class.
     * @throws EmptyDescriptionException Exception thrown if str has no arguments.
     */
    private static Command parseDeleteCommand(String str) throws EmptyDescriptionException {
        Matcher deleteMatcher = delete.matcher(str);
        if (deleteMatcher.find()) {
            int taskNum = Integer.parseInt(deleteMatcher.group(1));
            return new DeleteCommand(taskNum);
        } else {
            throw new EmptyDescriptionException("delete");
        }
    }


    /**
     * Parses the string str into a Done Command
     *
     * @param str String to be parsed
     * @return Object of the Done Command Class.
     * @throws EmptyDescriptionException Exception thrown if str has no arguments.
     */
    private static Command parseDoneCommand(String str) throws EmptyDescriptionException {
        Matcher doneMatcher = done.matcher(str);
        if (doneMatcher.find()) {
            int taskNum = Integer.parseInt(doneMatcher.group(1));
            return new DoneCommand(taskNum);
        } else {
            throw new EmptyDescriptionException("done");
        }
    }

    /**
     * Static method that takes in a string and converts it into a LocalDateTime object
     *
     * @param dateString String representing a date in dd/MM/yyyy HHmm format
     * @return Object of the LocalDateTime class.
     * @throws InvalidDateException Exception when the format of the string is invalid.
     */
    public static LocalDateTime parseDate(String dateString) throws InvalidDateException {
        Matcher dateMatcher = datePattern.matcher(dateString);
        try {
            if (dateMatcher.find()) {
                int day = Integer.parseInt(dateMatcher.group(1));
                int month = Integer.parseInt(dateMatcher.group(2));
                int year = Integer.parseInt(dateMatcher.group(3));
                int hour = Integer.parseInt(dateMatcher.group(4).substring(0, 2));
                int minutes = Integer.parseInt(dateMatcher.group(4).substring(2));
                return LocalDateTime.of(year, month, day, hour, minutes);
            } else {
                throw new InvalidDateException();
            }
        } catch (Exception e) {
            throw new InvalidDateException();
        }
    }

}
