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
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma");

    // instance variables
    private LocalDateTime date;

    // constructors
    public Event(String desc, LocalDateTime date) {
        super(desc);
        this.date = date;
    }
    public Event(String desc, LocalDateTime date, boolean isDone) {
        super(desc, isDone);
        this.date = date;
    }

    // private helper for returning formatted dates
    private String[] format(LocalDateTime date) {
        return date.format(format).split(" ");
    }

    // String representation methods
    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", TYPE, getStateLabel(),
                format(date)[0], format(date)[1]);
    }
    @Override
    public String databaseRep() {
        return TYPE + " | " + super.databaseRep() + " | "
                + format(date)[0] + " " + format(date)[1];
    }
}
