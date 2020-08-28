/**
 * Encapsulates the idea of events, tasks that start
 * and end at specific times. Thus it inherits from Task
 */
package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    // constants
    private static final String TYPE = "E";
    private static final DateTimeFormatter DATABASE_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma");

    // instance variables
    private LocalDateTime date;

    // constructors
    /**
     * Constructs an event object that is not completed
     * given details in the form of a description and a
     * LocalDateTime object detailing the date of event
     *
     * @param desc description of the event
     * @param date the date of event as a DateTime object
     */
    public Event(String desc, LocalDateTime date) {
        super(desc);
        this.date = date;
    }

    /**
     * Constructs an event object given details in
     * the form of a description, a LocalDateTime
     * object detailing the date of event and the
     * completion of the event
     *
     * @param desc description of the event
     * @param date the date of event as a DateTime object
     * @param isDone completion state of event
     */
    public Event(String desc, LocalDateTime date, boolean isDone) {
        super(desc, isDone);
        this.date = date;
    }

    // private helper for returning formatted dates
    private String[] format(LocalDateTime date) {
        return date.format(DATABASE_DATE_TIME_FORMAT).split(" ");
    }

    // String representation methods
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", TYPE, getStateLabel(),
                getDesc(), format(date)[0] + " " + format(date)[1]);
    }
    @Override
    public String databaseRep() {
        return TYPE + " | " + super.databaseRep() + " | "
                + format(date)[0] + " " + format(date)[1];
    }
}
