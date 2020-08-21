import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private static final String identifier = "D";
    private LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", Deadline.identifier, super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    @Override
    public String serialise() {
        return String.format("%s | %s | %s", Deadline.identifier, super.serialise(),
                this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }
}
