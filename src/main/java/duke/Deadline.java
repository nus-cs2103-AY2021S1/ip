package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represent a deadline event
 */
public class Deadline extends Task {

    public final LocalDate deadline;

    Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = Parser.parseDate(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateTimeFormatter.ofPattern("MMM d yyyy").format(deadline) + ")";
    }

    /**
     * @return the representation of the event when written to disk
     */
    @Override
    protected String toDisk() {
        return String.format("deadline\n%s\n%d\n%s", desc, (isDone ? 1 : 0), deadline);
    }
}
