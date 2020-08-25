import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {

    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + " "
                + time.format(DateTimeFormatter.ofPattern("h:mm a")) + ")";
    }
}