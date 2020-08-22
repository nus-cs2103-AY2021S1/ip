import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate date;
    private LocalTime time;

    Event(String name, LocalDate date, LocalTime time, boolean done) {
        super(name, done);
        this.time = time;
        this.date = date;
    }

    Event(String name, LocalDate date, boolean done) {
        super(name, done);
        this.time = null;
        this.date = date;
    }

    public String appendFile() {
        String doneString = (done == true ? "1" : "0");
        return "event" + " | " + doneString + " | " + this.name + " | " + this.date + " | "
                + this.time.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        String doneString = (super.done == true ? "✓" : "✗");
        String dateFormat = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + "[" + doneString + "] " + this.name + " (at: " + dateFormat
                + (this.time != null ? " " + this.time.format(DateTimeFormatter.ofPattern("HHmma")) + " " : "") + ")";
    }
}
