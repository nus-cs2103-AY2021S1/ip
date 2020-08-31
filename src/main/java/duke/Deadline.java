package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Represent a deadline event
 */
public class Deadline extends Task {

    private final LocalDate deadline;

    Deadline(String desc, String deadline) {
        super(desc);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * @return the representation of the event when written to disk
     */
    @Override
    protected String toDisk() {
        return String.format("deadline\n%s\n%d\n%s", desc, (done ? 1 : 0), deadline);
    }
}