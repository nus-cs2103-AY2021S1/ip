package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Arrays;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.DueCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.task.TaskType;

/**
 * The command line parser.
 */
public class Parser {
    /**
     * Parses a string and gives a datetime for use in creating tasks.
     *
     * @param dateTime A string representing a date and a time.
     * @return a LocalDateTime object represented by the string.
     * @throws DukeException if the string cannot be parsed.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        String[] dateTimes = dateTime.split("\\s+");
        String date = dateTimes[0];
        String time = dateTimes.length > 1 ? String.join(" ", Arrays.copyOfRange(dateTimes, 1, dateTimes.length)) : "";

        return LocalDateTime.of(Parser.parseDate(date), Parser.parseTime(time));
    }

    /**
     * Parses a string and gives a date for use in creating tasks.
     *
     * @param dateStr A string representing a date.
     * @return a LocalDate object represented by the string.
     * @throws DukeException if the string cannot be parsed.
     */
    public static LocalDate parseDate(String dateStr) throws DukeException {
        LocalDate date;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d/M[/yyyy][/yy]");

        try {
            TemporalAccessor result = dateFormatter.parseBest(dateStr, LocalDate::from, MonthDay::from);

            if (result instanceof LocalDate) {
                date = ((LocalDate) result);
            } else {
                date = ((MonthDay) result).atYear(Year.now().getValue());
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("Unable to parse date.\n \n"
                    + "Please input your date in one of the following formats:\n"
                    + "26/08\n" + "26/08/20\n" + "26/08/2020");
        }

        return date;
    }

    /**
     * Parses a string and gives a time for use in creating tasks.
     *
     * @param timeStr A string representing a time.
     * @return a LocalTime object represented by the string.
     * @throws DukeException if the string cannot be parsed.
     */
    public static LocalTime parseTime(String timeStr) throws DukeException {
        LocalTime time = LocalTime.MIDNIGHT;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(timeStr.contains(" ") ? "h:m a" : "H:m");

        if (!timeStr.isBlank()) {
            try {
                time = timeFormatter.parse(timeStr, LocalTime::from);
            } catch (DateTimeParseException e) {
                throw new DukeException("Unable to parse time.\n \n"
                        + "Please input your time in one of the following formats:\n"
                        + "1:19\n" + "1:19 AM");
            }
        }

        return time;
    }

    /**
     * Parses the command given and returns a Command object for execution.
     *
     * @param fullCommand A string containing the full command.
     * @return a Command object representing the command.
     * @throws DukeException if the command cannot be parsed.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] commands = fullCommand.split("\\s");
        String command = commands[0];
        String args = commands.length > 1 ? String.join(" ", Arrays.copyOfRange(commands, 1, commands.length)) : "";

        switch (command) {
        case "bye":
            if (!args.isBlank()) {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }

            return new ExitCommand();
        case "list":
            if (!args.isBlank()) {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }

            return new ListCommand();
        case "due":
            if (args.isBlank()) {
                throw new DukeException("Date required for the due command.");
            }

            return new DueCommand(Parser.parseDate(args));
        case "done":
            if (args.isBlank()) {
                throw new DukeException("Task number required for the done command.");
            } else if (!args.chars().allMatch(Character::isDigit)) {
                throw new DukeException("Only positive integers allowed for the done command.");
            }

            return new DoneCommand(Integer.parseInt(args));
        case "delete":
            if (args.isBlank()) {
                throw new DukeException("Task number required for the delete command.");
            } else if (!args.chars().allMatch(Character::isDigit)) {
                throw new DukeException("Only positive integers allowed for the delete command.");
            }

            return new DeleteCommand(Integer.parseInt(args));
        case "find":
            if (args.isBlank()) {
                throw new DukeException("Keyword cannot be blank.");
            }

            return new FindCommand(args);
        case "todo":
            return new AddCommand(TaskType.TODO, args);
        case "deadline": {
            String[] deadlineArgs = args.split("\\s/by\\s");
            String description = deadlineArgs[0];
            String by = deadlineArgs.length > 1
                    ? String.join(" ", Arrays.copyOfRange(deadlineArgs, 1, deadlineArgs.length))
                    : "";

            return new AddCommand(TaskType.DEADLINE, description, Parser.parseDateTime(by));
        }
        case "event": {
            String[] eventArgs = args.split("\\s/at\\s");
            String description = eventArgs[0];
            String at = eventArgs.length > 1
                    ? String.join(" ", Arrays.copyOfRange(eventArgs, 1, eventArgs.length))
                    : "";

            return new AddCommand(TaskType.EVENT, description, Parser.parseDateTime(at));
        }
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }
}
