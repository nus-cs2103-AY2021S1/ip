package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a sub-class of Task. Deadline is a task with a deadline date which
 * means this Task has to be done before the deadline date.
 */
public class Deadline extends Task {

    /** Date as LocalDate. */
    protected LocalDate date;
    /** Date as String. */
    protected String date2;

    /**
     * Constructs a Deadline.
     *
     * @param description The description of the Deadline.
     * @param date The deadline date of the deadline.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
        this.date2 = date;
    }

    /**
     * Gets the information about this Deadline except its status.
     *
     * @return a String array that contains symbol type for Event ("E"), description, and date.
     */
    @Override
    public String[] getInfo() {
        return new String[] {"D", description, date2};
    }

    /**
     * Returns String representation of this Deadline.
     *
     * @return String that represent this Deadline.
     */
    @Override
    public String toString() {
        String dateString = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return " [D]" + super.toString() + " (by: " + dateString + ")";
    }
}
