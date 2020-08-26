import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    public Deadline(String description, LocalDate date) {
        super(description, date);
    }

    public Deadline(String description, LocalDate date, String duration) {
        super(description, date, duration);
    }

    public Deadline(String description, String dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        String print = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return duration == null
                ? "[D]" + super.toString() + " (by: " + print + ")"
                : "[D]" + super.toString() + " (by: " + print + " " + duration + ")";
    	}
}