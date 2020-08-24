package duke;

import command.*;
import exception.DukeException;
import exception.EmptyDescriptionException;
import exception.InvalidDateException;
import exception.InvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    static Pattern todo = Pattern.compile("todo (.+)");
    static Pattern deadline = Pattern.compile("deadline (.+?) /by (.+)");
    static Pattern event = Pattern.compile("event (.+?) /at (.+)");
    static Pattern done = Pattern.compile("done ([0-9]+)");
    static Pattern delete = Pattern.compile("delete ([0-9]+)");
    static Pattern datePattern = Pattern.compile("^([0-9]{1,2})[/-]([0-9]{1,2})[/-]([0-9]{4}) ([0-9]{4})$");
    static Pattern find = Pattern.compile("find (.+)");

    /**
     * Static method that takes in a String and returns a Command
     * @param str String to be parsed
     * @return Object of the Command Class
     * @throws DukeException Any exceptions with parsing
     */
    public static Command parse(String str) throws DukeException {
        if (str.equals("bye")) {
            return new ByeCommand();
        } else if (str.equals("list")) {
            return new ListCommand();
        } else {
            switch (str.split(" ")[0]) {
                case "done":
                    Matcher doneMatcher = done.matcher(str);
                    if (doneMatcher.find()) {
                        int taskNum = Integer.parseInt(doneMatcher.group(1));
                        return new DoneCommand(taskNum);
                    } else {
                        throw new EmptyDescriptionException("done");
                    }

                case "delete":
                    Matcher deleteMatcher = delete.matcher(str);
                    if (deleteMatcher.find()) {
                        int taskNum = Integer.parseInt(deleteMatcher.group(1));
                        return new DeleteCommand(taskNum);
                    } else {
                        throw new EmptyDescriptionException("delete");
                    }
                case "todo":
                    Matcher todoMatcher = todo.matcher(str);
                    if (todoMatcher.find()) {
                        String todo = todoMatcher.group(1);
                        return new TodoCommand(todo);
                    } else {
                        throw new EmptyDescriptionException("todo");
                    }
                case "deadline":
                    Matcher deadlineMatcher = deadline.matcher(str);
                    if (deadlineMatcher.find()) {
                        LocalDateTime deadLine = Parser.parseDate(deadlineMatcher.group(2));
                        return new DeadlineCommand(deadlineMatcher.group(1), deadLine);
                    } else {
                        throw new EmptyDescriptionException("deadline");
                    }
                case "event":
                    Matcher eventMatcher = event.matcher(str);
                    if (eventMatcher.find()) {
                        LocalDateTime timing = Parser.parseDate(eventMatcher.group(2));
                        return new EventCommand(eventMatcher.group(1), timing);
                    } else {
                        throw new EmptyDescriptionException("event");
                    }
                case "find":
                    Matcher findMatcher = find.matcher(str);
                    if (findMatcher.find()) {
                        return new FindCommand(findMatcher.group(1));
                    } else {
                        throw new EmptyDescriptionException("find");
                    }
                default:
                    throw new InvalidInputException();
            }
        }
    }

    /**
     * Static method that takes in a string and converts it into a LocalDateTime object
     * @param dateString String representing a date in dd/MM/yyyy HHmm format
     * @return Object of the LocalDateTime class
     * @throws InvalidDateException Exception when the format of the string is invalid
     */

    public static LocalDateTime parseDate(String dateString) throws InvalidDateException {
        Matcher dateMatcher = datePattern.matcher(dateString);
        try {
            if (dateMatcher.find()) {
                String day = dateMatcher.group(1);
                if (day.length() == 1) {
                    day = "0" + day;
                }
                String month = dateMatcher.group(2);

                if (month.length() == 1) {
                    month = "0" + month;
                }
                String year = dateMatcher.group(3);
                String time = dateMatcher.group(4);
                LocalDateTime localDateTime = LocalDateTime.parse(String.format("%s-%s-%s %s", day,month,year,time), DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
                return localDateTime;
            } else {
                throw new InvalidDateException();
            }
        }
        catch (Exception e){
            throw new InvalidDateException();
        }
    }


}
