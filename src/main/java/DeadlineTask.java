import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    LocalDateTime deadline;

    DeadlineTask(String description, String deadline) {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch (DateTimeParseException exception) {
                System.out.println(exception);
        }

    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("d MM yyyy, hh:mm a")) + ")";
    }

}
