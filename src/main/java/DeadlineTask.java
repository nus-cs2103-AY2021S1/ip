import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    LocalDateTime deadline;

    DeadlineTask(String description, String deadline) throws DukeException {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            this.deadline = LocalDateTime.parse(deadline, formatter);
        } catch (DateTimeParseException exception) {
            System.out.println(deadline);
            throw new DukeException("Error! Invalid date format, Please enter the date in the format dd-MM-yyyy HH:mm");
    }

    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " +
                deadline.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")";
    }

}
