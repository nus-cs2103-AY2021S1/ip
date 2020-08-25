import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class Deadline extends Task {
    LocalDate deadline;

    DateTimeFormatter in = DateTimeFormatter.ISO_LOCAL_DATE;
    DateTimeFormatter out = DateTimeFormatter.ofPattern("MMM dd yyyy");

    Deadline(String name, String time) throws DateTimeParseException {
        super(name, Type.DEADLINE);
        try {
            this.deadline = LocalDate.parse(time, in);
        } catch (DateTimeParseException e) {
            throw e;
        }
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(out) + ")";
    }
}
