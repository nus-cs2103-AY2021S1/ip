import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
