package duckie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected final static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E, MMM dd yyyy");
    protected LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getDate() {
        return dtf.format(this.deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dtf.format(this.deadline) + ")";
    }
}
