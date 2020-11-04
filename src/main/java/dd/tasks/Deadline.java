package dd.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A deadline task with a specific due date or date and time.
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Class Constructor.
     *
     * @param description Description of deadline.
     * @param by Due date or date and time of deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        assert !(description.equals("")) : "deadline item is empty";
        assert !(by.equals("")) : "deadline date is empty";
    }

    /**
     * Returns due date of deadline.
     *
     * @return String of due date of deadline.
     */
    public String getDate() {
        int dateLength = 11;

        return by.substring(0, dateLength);
    }

    /**
     * Returns LocalDate or LocalDateTime form of date or date and time of deadline.
     *
     * @return LocalDate or LocalDateTime form of date or date and time of deadline.
     */
    @Override
    public LocalDateTime getDateTime() {
        int dateLength = 11;
        int dateTimeLength = 20;

        assert by.length() == dateLength || by.length() == dateTimeLength : "by string is not valid";

        if (by.length() == dateLength) {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy");
            return LocalDate.parse(by, format).atStartOfDay();
        } else {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
            return LocalDateTime.parse(by, format);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveString() {
        if (this.isDone) {
            return "D , 1 , " + description + " , " + by;
        } else {
            return "D , 0 , " + description + " , " + by;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
