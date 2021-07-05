package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Encapsulates the idea of deadlines, tasks that need to
 * be done before a specific time. Thus it inherits from Task
 */
public class Deadline extends Task {

    // constants
    private static final String TYPE = "D";
    private static final DateTimeFormatter DATABASE_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma");

    // instance variables
    private LocalDateTime date;

    // constructors

    /**
     * Constructs a deadline object that is not completed
     * given its description and the deadline date given
     * as a LocalDateTime date object
     *
     * @param desc the description of the deadline
     * @param date the date of the deadline
     */
    public Deadline(String desc, LocalDateTime date) {
        super(desc);
        this.date = date;
    }

    /**
     * Constructs a deadline object given details in
     * the form of a description, a LocalDateTime
     * object detailing the date of deadline and the
     * completion of the deadline
     *
     * @param desc description of the deadline
     * @param date the date of deadline as a DateTime object
     * @param isDone completion state of deadline
     */
    public Deadline(String desc, LocalDateTime date, boolean isDone) {
        super(desc, isDone);
        this.date = date;
    }

    // private helper for returning formatted dates
    private String[] formatDate(LocalDateTime date) {
        return date.format(DATABASE_DATE_TIME_FORMAT).split(" ");
    }

    // accessor method for date and time
    public LocalDateTime getDate() {
        return date;
    }

    // String representation methods
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", TYPE, getStateLabel(),
                getDesc(), formatDate(date)[0] + " " + formatDate(date)[1]);
    }
    @Override
    public String databaseRep() {
        return TYPE + " | " + super.databaseRep() + " | "
                + formatDate(date)[0] + " " + formatDate(date)[1];
    }
}
