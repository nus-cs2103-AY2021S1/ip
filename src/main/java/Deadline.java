import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Deadline is a type of Task with deadline.
 */
class Deadline extends Task {
    private LocalDate deadline;

    Deadline(String name, String time) {
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
