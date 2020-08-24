import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate deadline;

    protected Deadline(String task, LocalDate deadline) {
        super(task);
        this.deadline = deadline;
    }

    private LocalDate getDeadline() {
        return this.deadline;
    }

    @Override
    public String toString() {
        String done = this.done ? "\u2713" : "\u2718";
        String date = this.deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D][" + done + "] " + this.task + "(by: " + date + ")";
    }
}
