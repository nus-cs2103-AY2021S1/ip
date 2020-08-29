import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate date;
    protected String time;

    public Deadline(String description, LocalDate by, String time) {
        super(description);
        this.date = by;
        this.time = time;
    }

    @Override
    public String toString() {
        String dateOutput = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + super.toString() + " (by: " + dateOutput + " " + time + ")";
    }
}