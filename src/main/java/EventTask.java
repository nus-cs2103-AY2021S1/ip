import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    LocalDateTime timing;

    EventTask(String description, String timing) {
        super(description);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            this.timing = LocalDateTime.parse(timing, formatter);
        } catch (DateTimeParseException exception) {
            System.out.println(exception);
        }
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (by: " +
                timing.format(DateTimeFormatter.ofPattern("d MM yyyy, hh:mm a")) + ")";
    }

}