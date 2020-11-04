package dd.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * An event task occurring on a specific date or date and time.
 */
public class Event extends Task {

    protected String at;

    /**
     * Class Constructor.
     *
     * @param description Description of event.
     * @param at Date or date and time of event.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        assert !(description.equals("")) : "event item is empty";
        assert !(at.equals("")) : "event date is empty";
    }

    /**
     * Returns date of event.
     *
     * @return String of date of event.
     */
    public String getDate() {
        int dateLength = 11;

        return at.substring(0, dateLength);
    }

    /**
     * Returns LocalDate or LocalDateTime form of date or date and time of event.
     *
     * @return LocalDate or LocalDateTime form of date or date and time of event.
     */
    @Override
    public LocalDateTime getDateTime() {
        int dateLength = 11;
        int dateTimeLength = 20;

        assert at.length() == dateLength || at.length() == dateTimeLength : "at string is not valid";

        if (at.length() == dateLength) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
            return LocalDate.parse(at, format).atStartOfDay();
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
            return LocalDateTime.parse(at, format);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveString() {
        if (this.isDone) {
            return "E , 1 , " + description + " , " + at;
        } else {
            return "E , 0 , " + description + " , " + at;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
