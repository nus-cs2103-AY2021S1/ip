import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate taskBy;

    Deadline(String taskName, LocalDate taskBy) {
        super(taskName);
        this.taskBy = taskBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.taskBy.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }


}