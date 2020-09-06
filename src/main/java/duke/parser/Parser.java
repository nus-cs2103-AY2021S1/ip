package duke.parser;

import duke.command.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            default:
                return new InvalidCommand();
        }
    }

    public static LocalDate parseDate(String date) {
        LocalDate localDate;
        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
            DayOfWeek day;
            switch(date) {
            case "Mon":
                day = DayOfWeek.MONDAY;
                break;
            case "Tue":
                day = DayOfWeek.TUESDAY;
                break;
            case "Wed":
                day = DayOfWeek.WEDNESDAY;
                break;
            case "Thu":
                day = DayOfWeek.THURSDAY;
                break;
            case "Fri":
                day = DayOfWeek.FRIDAY;
                break;
            case "Sat":
                day = DayOfWeek.SATURDAY;
                break;
            case "Sun":
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
