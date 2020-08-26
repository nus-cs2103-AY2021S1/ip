/**
 * Encapsulates the idea of deadlines, tasks that need to
 * be done before a specific time. Thus it inherits from Task
 */
package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    // constants
    private static final String TYPE = "D";
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy h:mma");

    // instance variables
    private LocalDateTime date;

    // constructors
    public Deadline(String desc, LocalDateTime date) {
        super(desc);
        this.date = date;
    }
    public Deadline(String desc, LocalDateTime date, boolean isDone) {
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
        return String.format("[%s][%s] %s (by: %s)", TYPE, getStateLabel(),
                format(date)[0], format(date)[1]);
    }
    @Override
    public String databaseRep() {
        return TYPE + " | " + super.databaseRep() + " | "
                + format(date)[0] + " " + format(date)[1];
    }
}
