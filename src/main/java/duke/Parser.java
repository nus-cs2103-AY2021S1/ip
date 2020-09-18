package duke;

import static java.time.temporal.TemporalAdjusters.next;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandInstruction;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ViewCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskType;
import duke.task.Todo;

/**
 * Deals with making sense of user inputs.
 */
public class Parser {
    /** List of all the valid date inputs */
    private static final List<String> DATE_FORMATS = Arrays.asList("d/M/yy", "d/MMM/yy", "d/MMMM/yy",
            "d/M/y", "d/MMM/y", "d/MMMM/y", "y/M/d",
            "d-M-yy", "d-MMM-yy", "d-MMMM-yy",
            "d-M-y", "d-MMM-y", "d-MMMM-y", "y-M-d",
            "d M yy", "d MMM yy", "d MMMM yy",
            "d M y", "d MMM y", "d MMMM y", "y M d");

    /**
     * Parses the input command from the user into a command that the chat bot can understand.
     *
     * @param fullCommand The command from the user.
     * @return The command that can be interpreted from the user.
     * @throws DukeException If there was some problems with understanding the user's commands.
     */
    public static Command parse(String fullCommand) throws DukeException {
        try {
            String[] splitCommand = fullCommand.trim().split(" ", 2);
            CommandInstruction instruction = parseCommandInstruction(splitCommand[0]);
            switch (instruction) {
            case LIST:
                return new ListCommand();
            case BYE:
                return new ExitCommand();
            case DONE:
                int taskNumberDone = Integer.parseInt(splitCommand[1]);
                return new DoneCommand(taskNumberDone);
            case DELETE:
                int taskNumberDelete = Integer.parseInt(splitCommand[1]);
                return new DeleteCommand(taskNumberDelete);
            case TODO:
                Todo todo = new Todo(splitCommand[1]);
                return new AddCommand(todo);
            case DEADLINE:
                Task deadline = parseDateTimeTask(TaskType.DEADLINE, splitCommand[1]);
                return new AddCommand(deadline);
            case EVENT:
                Task event = parseDateTimeTask(TaskType.EVENT, splitCommand[1]);
                return new AddCommand(event);
            case VIEW:
                LocalDate viewDate = parseDate(splitCommand[1]);
                return new ViewCommand(viewDate);
            case FIND:
                return new FindCommand(splitCommand[1]);
            default:
                // Invalid instruction.
                assert false : instruction;
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (NumberFormatException e) {
            // When the number given for done and delete is not valid.
            throw new DukeException("OOPS!!! The task number is not valid.");
        } catch (IllegalArgumentException e) {
            // When the command cannot be understood.
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (IndexOutOfBoundsException e) {
            // When there is no description after the command.
            throw new DukeException("OOPS!!! The description cannot be empty.");
        }
    }

    /**
     * Parses the input command instruction from the user to a command instruction this chat bot can understand.
     *
     * @param userInstruction The command instruction in string from the user.
     * @return Command instruction this chat bot can understand.
     */
    private static CommandInstruction parseCommandInstruction(String userInstruction) {
        return CommandInstruction.valueOf(userInstruction.toUpperCase());
    }

    /**
     * Parses the input into a task of the specified task type with description, date and optionally time.
     *
     * @param taskType Type of the task to be returned.
     * @param input The description, date and optionally time in the format: description /separator date time.
     * @return Task of the specified task type, description, date and optionally time.
     * @throws DukeException If there was a problem with creating a valid task.
     */
    public static Task parseDateTimeTask(TaskType taskType, String input) throws DukeException {
        input = input.trim();
        String description;
        String dateTimeString;
        String potentialDate;
        String potentialTime;
        LocalDate localDate;
        LocalTime localTime;
        // Check if there is a description or not.
        if (input.startsWith("/")) {
            throw new DukeException("OOPS!!! The description cannot be empty.");
        }
        // Try to split description and date.
        try {
            switch (taskType) {
            case DEADLINE:
                String[] splitDeadline = input.split(" /by ", 2);
                description = splitDeadline[0];
                dateTimeString = splitDeadline[1].replaceAll(" +", " ");
                break;
            case EVENT:
                String[] splitEvent = input.split(" /at ", 2);
                description = splitEvent[0];
                dateTimeString = splitEvent[1].replaceAll(" +", " ");
                break;
            default:
                assert false;
                throw new DukeException("OOPS!!! There were some problems in making the task.");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("OOPS!!! The date or time cannot be empty.");
        }
        // Try to parse time if it exists.
        try {
            potentialTime = dateTimeString.substring(dateTimeString.lastIndexOf(" ") + 1);
            localTime = parseTime(potentialTime);
        } catch (DukeException e) {
            localTime = null;
        }
        // Parse date.
        if (localTime == null) {
            potentialDate = dateTimeString;
        } else {
            potentialDate = dateTimeString.substring(0, dateTimeString.lastIndexOf(" "));
        }
        try {
            localDate = parseDate(potentialDate);
        } catch (DukeException e) {
            if (localTime != null) {
                throw new DukeException("OOPS!!! The date is not valid.");
            } else {
                throw new DukeException("OOPS!!! The date or time is not valid.");
            }
        }
        // Returns new deadline / event with the date and optionally time.
        switch (taskType) {
        case DEADLINE:
            return new Deadline(description, localDate, localTime);
        case EVENT:
            return new Event(description, localDate, localTime);
        default:
            // Invalid task type.
            assert false;
            throw new DukeException("OOPS!!! There were some problems in making the task.");
        }
    }

    /**
     * Parses input dates in the valid date formats to the local date format. Valid date formats include:
     * <ol>
     *     <li>day/month/year</li>
     *     <li>day-month-year</li>
     *     <li>day month year</li>
     *     <li>Days of the week and their abbreviations like Monday, Mon etc. </li>
     *     <li>Relative days: yesterday, today, tomorrow. </li>
     * </ol>
     * where day can be 03 or 3, month can be 09, 9, Sep, September, and year can be 2011 or 11.
     * It can also parse dates that are y-M-d, y/M/d, y M d provided they are in the full numerical representations
     * of year, month and day e.g. 2011-01-11.
     *
     * @param date The date in string to be parsed.
     * @return Local date format of the input date.
     * @throws DukeException If date cannot be parsed.
     */
    private static LocalDate parseDate(String date) throws DukeException {
        String parsedInput = date.trim().toUpperCase();
        try {
            return parseLocalDateFromStringDate(parsedInput);
        } catch (IllegalArgumentException e) {
            // Try the other ways to parse the date first.
        }
        try {
            return parseLocalDateFromDay(parsedInput);
        } catch (IllegalArgumentException e) {
            // Try the other ways to parse the date first.
        }
        try {
            return parseLocalDateFromRelativeDay(parsedInput);
        } catch (IllegalArgumentException e) {
            // Try the other ways to parse the date first.
        }
        // By reaching here, it means that the date cannot be parsed and an exception is thrown.
        throw new DukeException("OOPS!!! The date is not valid.");
    }

    /**
     * Parses input dates in the valid date formats to the local date format. Valid date formats include:
     * <ol>
     *     <li>day/month/year</li>
     *     <li>day-month-year</li>
     *     <li>day month year</li>
     * </ol>
     * where day can be e.g. 03 or 3, month can be e.g. 09, 9, Sep, September, and year can be e.g. 2011 or 11.
     * It can also parse dates that are y-M-d, y/M/d, y M d provided they are in the full numerical representations
     * of year, month and day e.g. 2011-01-11.
     *
     * @param date The date in string to be parsed.
     * @return Local date format of the input date.
     * @throws IllegalArgumentException If the date cannot be parsed as any of the accepted date formats.
     */
    private static LocalDate parseLocalDateFromStringDate(String date) {
        for (String format : DATE_FORMATS) {
            try {
                DateTimeFormatter dTF =
                        new DateTimeFormatterBuilder().parseCaseInsensitive()
                                .appendPattern(format)
                                .toFormatter();
                return LocalDate.parse(date, dTF);
            } catch (DateTimeParseException e) {
                // We want to go through all the date formats first.
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * Parses day of the week in string to the next such day in local date format.
     * Days of the week are Monday to Sunday.
     *
     * @param day The day of the week in string to be parsed.
     * @return Local date format of the day.
     * @throws IllegalArgumentException If the day cannot be parsed as a day of the week.
     */
    private static LocalDate parseLocalDateFromDay(String day) {
        DayOfWeek parsedDay;
        switch (day) {
        case "MONDAY": case "MON":
            parsedDay = DayOfWeek.MONDAY;
            break;
        case "TUESDAY": case "TUE": case "TUES":
            parsedDay = DayOfWeek.TUESDAY;
            break;
        case "WEDNESDAY": case "WED":
            parsedDay = DayOfWeek.WEDNESDAY;
            break;
        case "THURSDAY": case "THU": case "THUR": case "THURS":
            parsedDay = DayOfWeek.THURSDAY;
            break;
        case "FRIDAY": case "FRI":
            parsedDay = DayOfWeek.FRIDAY;
            break;
        case "SATURDAY": case "SAT":
            parsedDay = DayOfWeek.SATURDAY;
            break;
        case "SUNDAY": case "SUN":
            parsedDay = DayOfWeek.SUNDAY;
            break;
        default:
            throw new IllegalArgumentException();
        }
        return LocalDate.now().with(next(parsedDay));
    }

    /**
     * Parses the relative day to the local date format. Relative days include yesterday, today and tomorrow.
     *
     * @param day The relative day in string to be parsed.
     * @return Local date format of the relative day.
     * @throws IllegalArgumentException If the day cannot be parsed as a relative day.
     */
    private static LocalDate parseLocalDateFromRelativeDay(String day) {
        switch (day) {
        case "YESTERDAY":
            return LocalDate.now().minusDays(1);
        case "TODAY":
            return LocalDate.now();
        case "TOMORROW":
            return LocalDate.now().plusDays(1);
        default:
            throw new IllegalArgumentException();
        }
    }

    /**
     * Parses input time in the format of HH:mm to the local time format. HH must be in the 24 hours format.
     *
     * @param time The time in string to be parsed.
     * @return The local time format of the input time.
     * @throws DukeException If time cannot be parsed.
     */
    private static LocalTime parseTime(String time) throws DukeException {
        try {
            DateTimeFormatter dTF = new DateTimeFormatterBuilder()
                    .parseCaseInsensitive()
                    .appendPattern("HH:mm")
                    .toFormatter();
            return LocalTime.parse(time, dTF);
        } catch (DateTimeParseException e) {
            // We want to go through all the possible time formats first.
            // Note however that currently there is only one accepted time format, HH:mm.
        }
        // By reaching here, it means that the time cannot be parsed and an exception is thrown.
        throw new DukeException("OOPS!!! The time is not valid.");
    }
}
