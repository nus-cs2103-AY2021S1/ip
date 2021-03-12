package duckie;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duckie.command.AddCommand;
import duckie.command.ByeCommand;
import duckie.command.Command;
import duckie.command.DeleteAllCommand;
import duckie.command.DeleteCommand;
import duckie.command.DoneCommand;
import duckie.command.FindCommand;
import duckie.command.ListCommand;
import duckie.command.SortDeadlineCommand;
import duckie.command.SortEventCommand;
import duckie.exception.DuckieException;
import duckie.exception.DuckieInvalidCommandException;
import duckie.exception.DuckieNoNumberInputException;
import duckie.task.Deadline;
import duckie.task.Event;
import duckie.task.Task;
import duckie.task.Todo;

/**
 * Responsible for the parsing of input Commands.
 */
public class Parser {
    private static boolean isAWord(String s) {
        return (s.length() > 0 && s.split("\\s+").length == 1);
    }

    /**
     * Parse the input Command to direct Duckie on what actions to carry out.
     *
     * @param fullCommand Input string command.
     * @return Specific Command to execute the instructions.
     * @throws DuckieException All the possible DuckieExceptions.
     */
    public static Command parse(String fullCommand) throws DuckieException {
        assert fullCommand instanceof String : "Command must be a String type.";
        String input = fullCommand.strip();
        String firstWord = input.split(" ", 2)[0].toLowerCase();
        if (firstWord.equals("bye")) {
            return new ByeCommand();
        } else if (firstWord.equals("list")) {
            return new ListCommand();
        } else {
            if (firstWord.equals("done") && !isAWord(input)) {
                int ind = parseDone(input);
                return new DoneCommand(ind);
            } else if (firstWord.equals("delete") && !isAWord(input)) {
                String description = input.split(" ")[1].strip();
                if (description.equals("all")) {
                    return new DeleteAllCommand();
                } else {
                    int ind = parseDelete(input, description);
                    return new DeleteCommand(ind);
                }
            } else if (firstWord.equals("todo") && !isAWord(input)) {
                Task t1 = parseTodo(input);
                return new AddCommand(t1);
            } else if (firstWord.equals("deadline") && !isAWord(input)) {
                if (input.contains("/")) {
                    Task t1 = parseDeadline(input);
                    return new AddCommand(t1);
                } else {
                    throw new DuckieException("Please use '/by' to indicate the date input.");
                }
            } else if (firstWord.equals("event") && !isAWord(input)) {
                if (input.contains("/")) {
                    Task t1 = parseEvent(input);
                    return new AddCommand(t1);
                } else {
                    throw new DuckieException("Please use '/at' to indicate the date input.");
                }
            } else if (firstWord.equals("find") && !isAWord(input)) {
                String keyword = parseFind(input);
                return new FindCommand(keyword);
            } else if (firstWord.equals("sort") && !isAWord(input)) {
                if (input.contains("deadline")) {
                    return new SortDeadlineCommand();
                } else if (input.contains("event")) {
                    return new SortEventCommand();
                } else {
                    throw new DuckieInvalidCommandException();
                }
            } else {
                throw new DuckieInvalidCommandException();
            }
        }
    }

    public static int parseDone(String input) throws DuckieException {
        int ind;
        try {
            ind = Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new DuckieNoNumberInputException();
        }
        return ind;
    }

    public static int parseDelete(String input, String description) throws DuckieException {
        try {
            Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException e) {
            throw new DuckieNoNumberInputException("Input 'delete all' if you want to clear all tasks.");
        }
        int ind = Integer.parseInt(description);
        return ind;
    }

    public static Task parseTodo(String input) {
        String todo = input.split(" ", 2)[1];
        Task t1 = new Todo(todo);
        return t1;
    }

    public static Task parseDeadline(String input) throws DuckieException {
        String[] splitted = input.split("/");
        if (isAWord(splitted[1]) || input.split(" ")[1].equals("/")) {
            throw new DuckieException("Please state a date in the format 'd MMM YYYY' after '/by'.\n"
                    + "\t" + "For example, 'deadline Quiz /by 21 Aug 2000'.");
        }
        String time = splitted[1].split(" ", 2)[1];
        String description = splitted[0].split(" ", 2)[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate date = LocalDate.parse(time, formatter);
        Task t1 = new Deadline(description, date);
        return t1;
    }

    public static Task parseEvent(String input) throws DuckieException {
        String[] splitted = input.split("/");
        if (isAWord(splitted[1])) {
            throw new DuckieException("Please state a date in the format 'd MMM YYYY HH:MM a' after '/at'.\n"
                    + "\t" + "For example, 'event Party /at 21 Aug 2000 07:20 PM'.");
        }
        String time = splitted[1].split(" ", 2)[1];
        String description = splitted[0].split(" ", 2)[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy hh:mm a");
        LocalDateTime dateTime = LocalDateTime.parse(time, formatter);
        Task t1 = new Event(description, dateTime);
        return t1;
    }

    public static String parseFind(String input) {
        String keyword = input.split(" ", 2)[1];
        return keyword;
    }
}
