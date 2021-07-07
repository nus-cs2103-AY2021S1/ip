package Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Tasks.Deadline is a type of Tasks.Task with deadline.
 */
public class Deadline extends Task {
    private LocalDate deadline;

    public Deadline(String name, String time) {
        super(name, Type.DEADLINE);
        try {
            this.deadline = LocalDate.parse(time, DATE_FORMAT_IN);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DATE_FORMAT_OUT) + ")";
    }
}
