import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private LocalDate by;

    public Deadline(String name, String by) throws DukeException {
        super(name);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException ex) {
            throw new DukeException("Invalid date format. Use yyyy-mm-dd");
        }
    }

    public static Deadline parse(String[] split) {
        Deadline deadline = new Deadline(split[2], split[3]);
        if (split[1].equals("1")) {
            deadline.markDone();
        }
        return deadline;
    }

    public String serialize() {
        return "D|" + super.serialize() + "|" + this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
