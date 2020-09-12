package shiro.parser;

import shiro.command.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * deals with making sense of the user command
 */
public class Parser {
    /**
     * takes the user's full input and returns the appropriate command that should be run based on the given input
     * @param fullCommand the entire input given by the user
     * @return the appropriate command based on the input given by the user
     */
    public static Command parse(String fullCommand) {
        assert fullCommand != null : "fullCommand should not be null";
        String firstWord = fullCommand.split(" ")[0];

        switch (firstWord) {
        case "list":
            return new ListCommand();
        case "done":
            return new DoneCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        case "todo":
            return new ToDoCommand(fullCommand);
        case "deadline":
            return new DeadlineCommand(fullCommand);
        case "event":
            return new EventCommand(fullCommand);
        case "find":
            return new FindCommand(fullCommand);
        case "clear":
            return new ClearCommand();
        case "help":
            return new HelpCommand();
        case "bye":
            return new ExitCommand();
        case "view":
            return new ViewCommand(fullCommand);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * takes the date as a string or as a day of the week and returns it as a LocalDate object.
     * if the string is given as a day of the week, it will automatically be taken as the next nearest date which that day falls on.
     * if the day given is the same as the current day of the week, the date of next week's day will be used instead of the current date.
     * @param date can either be the date in string form in the format <yyyy-mm-dd> or <daydayday>
     * @return
     */
    public static LocalDate parseDate(String date) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
            DayOfWeek day;
            switch (date) {
            case "mon":
                day = DayOfWeek.MONDAY;
                break;
            case "tue":
                day = DayOfWeek.TUESDAY;
                break;
            case "wed":
                day = DayOfWeek.WEDNESDAY;
                break;
            case "thu":
                day = DayOfWeek.THURSDAY;
                break;
            case "fri":
                day = DayOfWeek.FRIDAY;
                break;
            case "sat":
                day = DayOfWeek.SATURDAY;
                break;
            case "sun":
                day = DayOfWeek.SUNDAY;
                break;
            default:
                throw new IllegalStateException("unexpected value: " + date);
            }
            int daysToAdd = (day.getValue() - currentDay.getValue() + 7) % 7;
            if (daysToAdd == 0) {
                daysToAdd = 7;
            }
            localDate = LocalDate.now().plusDays(daysToAdd);
        }
        return localDate;
    }
}
