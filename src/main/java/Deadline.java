import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate timeDescription;

    public Deadline(String description, LocalDate timeDescription) {
        super(description, "D");
        this.timeDescription = timeDescription;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + description
                + "(by: " + this.timeDescription.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
