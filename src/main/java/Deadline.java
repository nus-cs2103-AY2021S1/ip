import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate timeDescription;
    private String timeDescriptionFormatted;

    public Deadline(String description, LocalDate timeDescription) {
        super(description, "D");
        this.timeDescription = timeDescription;
        this.timeDescriptionFormatted = this.timeDescription.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public Deadline(String description, String timeDescriptionFormatted, boolean isDone) {
        super(description, "D", isDone);
        this.timeDescriptionFormatted = timeDescriptionFormatted;
    }

    @Override
    public String toString() {
        return "[" + getType() + "]" + "[" + getStatusIcon() + "] " + description
                + "(by: " + this.timeDescriptionFormatted + ")";
    }
}
