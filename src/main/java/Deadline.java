import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    // @@author Damith C. Rajapakse
    // Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html with minor modifications
    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, boolean isDone, LocalDateTime by) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return Time.convertTimeToSave(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + Time.toString(by) + ")";
    }
}
