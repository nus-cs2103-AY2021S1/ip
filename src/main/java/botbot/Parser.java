package botbot;

import botbot.commands.AddCommand;
import botbot.commands.Command;
import botbot.commands.DeleteCommand;
import botbot.commands.ExitCommand;
import botbot.commands.FindCommand;
import botbot.commands.InvalidCommand;
import botbot.commands.ListCommand;
import botbot.commands.MarkAsDoneCommand;
import botbot.exceptions.EmptySearchException;
import botbot.exceptions.EmptyTaskException;
import botbot.exceptions.EmptyTaskNumberException;
import botbot.exceptions.InvalidFormatException;
import botbot.exceptions.NoSuchCommandException;
import botbot.tasks.Deadline;
import botbot.tasks.Event;
import botbot.tasks.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser.
 */
public class Parser {
    private static final String NO_TIME_FLAG = String.format(" 0%.0f", Math.PI * Math.pow(10, 13));

    /**
     * Parses the given user input into a command.
     * 
     * @param input Input to be parsed.
     * @return Command parsed from input.
     * @throws EmptyTaskException If no task description is provided when adding task to task list.
     * @throws EmptyTaskNumberException If no task number is provided when deleting or marking task as done.
     * @throws InvalidFormatException If input is of an invalid format.
     * @throws NoSuchCommandException If input does not match any command.
     */
    static Command parseCommand(String input) throws EmptyTaskException, EmptyTaskNumberException,
            InvalidFormatException, NoSuchCommandException, EmptySearchException {
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.length() >= 8 && input.substring(0, 7).equals("delete ")) {
            try {
                int id = parseCommandId(input, "delete ");
                return new DeleteCommand(id);
            } catch (NumberFormatException e) {
                return new InvalidCommand("    oops! invalid task number!\n");
            }
        } else if (input.equals("delete") || (input.length() >= 7 && input.substring(0, 7).equals("delete "))) {
            throw new EmptyTaskNumberException();
        } else if (input.length() >= 6 && input.substring(0, 5).equals("done ")) {
            try {
                int id = parseCommandId(input, "done ");
                return new MarkAsDoneCommand(id);
            } catch (NumberFormatException e) {
                return new InvalidCommand("    oops! invalid task number!\n");
            }
        } else if (input.equals("done") || (input.length() >= 5 && input.substring(0, 5).equals("done "))) {
            throw new EmptyTaskNumberException();
        } else if (input.length() >= 6 && input.substring(0, 5).equals("todo ")) {
            return new AddCommand(new Todo(input));
        } else if (input.equals("todo") || (input.length() >= 5 && input.substring(0, 5).equals("todo "))) {
            throw new EmptyTaskException("a todo");
        } else if (input.length() >= 10 && input.substring(0, 9).equals("deadline ")) {
            if (!input.contains(" /by ")) {
                throw new InvalidFormatException(Deadline.FORMAT);
            }
            return new AddCommand(new Deadline(input));
        } else if (input.equals("deadline")
                || (input.length() >= 9 && input.substring(0, 9).equals("deadline "))) {
            throw new EmptyTaskException("a deadline");
        } else if (input.length() >= 7 && input.substring(0, 6).equals("event ")) {
            if (!input.contains(" /at ")) {
                throw new InvalidFormatException(Event.FORMAT);
            }
            return new AddCommand(new Event(input));
        } else if (input.equals("event")
                || (input.length() >= 6 && input.substring(0, 6).equals("event "))) {
            throw new EmptyTaskException("an event");
        } else if (input.length() >= 6 && input.substring(0, 5).equals("find ")) {
            String keyword = input.substring(5);
            return new FindCommand(keyword);
        } else if (input.equals("find") || (input.length() >= 5 && input.substring(0, 5).equals("find "))) {
            throw new EmptySearchException();
        } else {
            throw new NoSuchCommandException();
        }
    }

    /**
     * Parses the input for a task ID.
     * 
     * @param input Input to be parsed.
     * @param command Command parsed from input.
     * @return Task ID parsed from input.
     */
    static int parseCommandId(String input, String command) {
        return Integer.parseInt(input.substring(command.length())) - 1;
    }

    /**
     * Parses the input for a date (and a time). 
     * 
     * @param input Input to be parsed.
     * @param identifier Identifier which comes before the date.
     * @return Date (and time) parsed from input.
     * @throws DateTimeParseException If input does not follow expected datetime format.
     */
    public static LocalDateTime parseDateTime(String input, String identifier) throws DateTimeParseException {
        int index = input.indexOf(identifier) + identifier.length() + 1;
        String dateStr = input.substring(index).strip();
        if (dateStr.length() < 13) {
            dateStr += NO_TIME_FLAG;
        }
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern("d-M-yyyy HHmm[ssn]"));
    }
}
