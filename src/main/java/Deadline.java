import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline that the user has.
 */
public class Deadline extends Task {
    private String by;
    private LocalDate byDate;
    private LocalDateTime byDateTime;

    /**
     * Initializes a deadline with its description, and when it will be.
     * @param description The description of the deadline.
     * @param by When the deadline will be.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.setByDateAndTime();
    }

    /**
     * Initializes a deadline with its description, whether it is done, and when it will be.
     * @param description The description of the deadline.
     * @param isDone Whether the deadline is done.
     * @param by When the event will be.
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        this.setByDateAndTime();
    }

    /**
     * Sets the date or datetime object of when this deadline will be upon initializing it, based on the String "by" input.
     * If the input cannot be parsed, no action will be done.
     * To set just the date, the format of "by" has to be "YYYY-MM-DD" e.g. "2019-03-04".
     * To set both the datetime and date, the format of "by" has to be "YYYY-MM-DD'T'HH:mm:ss" e.g. "2019-03-04T00:05:02".
     */
    private void setByDateAndTime() {
        try {
            this.byDateTime = LocalDateTime.parse(this.by);
            this.byDate = this.byDateTime.toLocalDate();
        } catch (DateTimeParseException e) {
            try {
                this.byDate = LocalDate.parse(this.by);
            } catch (DateTimeParseException ignored) { }
        }
    }

    public String getBy() {
        return this.by;
    }

    private String getFormattedBy() {
        if (this.byDateTime != null) {
            return this.byDateTime.format(DateTimeFormatter.ofPattern("MMM d yyyy, HH:mm:ss a"));
        } else if (this.byDate != null) {
            return this.byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        } else {
            return this.by;
        }
    }

    /**
     * Returns whether the deadline is happening on a particular date.
     * @param date The date.
     * @return Whether the deadline is due by the date.
     */
    public boolean isOnDate(LocalDate date) {
        if (this.byDate != null) {
            return date.compareTo(this.byDate) == 0;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getFormattedBy() + ")";
    }
}