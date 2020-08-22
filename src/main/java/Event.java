import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private LocalDate date;
    private LocalTime time;

    Event(String name, LocalDate date, LocalTime time) {
        super(name);
        this.time = time;
        this.date = date;
    }

    Event(String name, LocalDate date) {
        super(name);
        this.time = null;
        this.date = date;
    }

    Event(String name, String time, boolean done) {
        super(name, done);
        this.time = time;
    }

    public String appendFile() {
        String doneString = (done == true ? "1" : "0");
        return "EVENT" + " | " + doneString + " | " + this.name + " | " + this.time;
    }

    @Override
    public String toString() {
        String doneString = (super.done == true ? "✓" : "✗");
        String dateFormat = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + "[" + doneString + "] " + this.name + " (at: " + dateFormat
                + (this.time != null ? " " + this.time.format(DateTimeFormatter.ofPattern("HHmma")) + " " : "") + ")";
    }
}
