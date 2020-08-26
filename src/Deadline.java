import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    LocalDateTime deadline;

    public Deadline(String description, String deadline) throws DateTimeParseException {
        super(description);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    public Deadline(String description, String deadline, Boolean isDone) {
        super(description, isDone);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + this.getIcon() + description + " (by: " +
                this.deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"))+ ")";
    }

    public String toSaveString() {
        return String.format("D | %s | %s | %s",
                super.doneString(), this.description, this.deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm")));
    }

}
