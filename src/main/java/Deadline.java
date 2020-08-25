import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description);
        this.date = date;
        this.time = time;
    }

    public LocalDate getDate() {
        return this.date;
    }

    @Override
    public String txtFileFormat() {
        return "D ~/~ " + super.txtFileFormat() + " ~/~ " + this.date.toString() +
                (this.time != null ? " ~/~ " + this.time.toString() : "");
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                " " + (time != null ? time.format(DateTimeFormatter.ofPattern("hh:mm a")) : "") + ")";
    }
}
