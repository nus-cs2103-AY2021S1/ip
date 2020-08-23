import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description, TaskType.DEADLINE);
        this.deadline = deadline;
    }

    public boolean isDateEqual(LocalDate date) {
        return date.equals(this.deadline);
    }

    private String getFormattedDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        return deadline.format(formatter);
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.getFormattedDeadline() + ")";
    }
}
