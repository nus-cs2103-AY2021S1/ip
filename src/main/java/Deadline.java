import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    LocalDate deadline;


    Deadline(String taskName, LocalDate date) {
        super(taskName);
        this.deadline = date;
    }

    Deadline(String taskName, LocalDate deadline, boolean isCompleted) {
       super(taskName,isCompleted);
       this.deadline = deadline;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String deadlineFormatted = deadline.format(formatter);
        return String.format("%s%s%s%s", "[D]", super.toString(), "(by: ", deadlineFormatted + ")");
    }
}
