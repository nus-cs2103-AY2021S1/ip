import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description, "D");
        this.deadline = deadline;
    }

    @Override
    public String generateSaveFormat() {
        return super.generateSaveFormat() + Duke.line + deadline;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" (by: %s)", deadline.format(
                        DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
