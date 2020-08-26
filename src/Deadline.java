import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task {

    public Deadline(String description, String by) {
        super(description);
        this.deadline = LocalDateTime.parse(by);
        this.type = "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDeadline() + ")";
    }

}
