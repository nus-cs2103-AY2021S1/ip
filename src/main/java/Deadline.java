import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate dueDate;

    public Deadline(String name, LocalDate details) {
        super(name);
        this.dueDate = details;
    }

    // Gets deadline of the task
    public String getDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return this.dueDate.format(formatter);
    }

    @Override
    public String toString() {
        // By default print task name
        return "[D]" + super.toString() + " (by: " + getDeadline() + ")";
    }
}
