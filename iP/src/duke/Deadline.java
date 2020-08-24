package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{

    protected LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
