package ekud.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Instantiates a new Deadline.
     *
     * @param description the description of the deadline as a <code>String</code>
     * @param by          the date by which the deadline is due
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public Deadline copy() {
        Deadline ret = new Deadline(description, by);
        ret.isDone = isDone;

        return ret;
    }

    @Override
    public String toString() {
        String deadlineBy = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(by);
        return "[D]" + super.toString() + " (by: " + deadlineBy + ")" + "\n";
    }
}
