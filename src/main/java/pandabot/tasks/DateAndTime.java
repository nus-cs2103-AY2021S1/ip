package pandabot.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Allows PandaBot to understand date and time in the format of dd/mm/yyyy hhmm
 */
public class DateAndTime {
    private final LocalDateTime dateAndTime;

    /**
     * Creates a DateAndTime object by taking in an input
     * of the format dd/mm/yyyy hhmm.
     *
     * @param dateAndTime the given input
     * @throws DateTimeParseException if the given input is not in the accepted format
     */
    public DateAndTime (String dateAndTime) throws DateTimeParseException {
        this.dateAndTime = LocalDateTime.parse(dateAndTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    private String parseDate(LocalDateTime input) {
        // Day of Month Counter
        String ending;
        int day = input.getDayOfMonth();
        switch (day % 10) {
        case 1:
            ending = "st";
            break;
        case 2:
            ending = "nd";
            break;
        case 3:
            ending = "rd";
            break;
        default:
            ending = "th";
            break;
        }
        return day + ending + " of " + input.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
    }

    private String parseTime(LocalDateTime input) {
        if (input.getMinute() == 0) {
            return input.format(DateTimeFormatter.ofPattern("ha"));
        } else {
            return input.format(DateTimeFormatter.ofPattern("h.mma"));
        }
    }

    /**
     * Returns a String representation of the DateAndTime object
     * in the format date of month year, hour.min AM/PM.
     *
     * @return a String representation of the DateAndTime object
     */
    @Override
    public String toString() {
        return parseDate(dateAndTime) + ", " + parseTime(dateAndTime);
    }
}
