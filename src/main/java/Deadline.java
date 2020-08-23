import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    LocalDate deadline;

    public Deadline(String description) {
        super(description.split(" /by ")[0]);
        this.deadline = LocalDate.parse(description.split(" /by ")[1]);
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.deadline.format(formatter) + ")";
    }
}
