import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate date;
    private LocalTime time;

    Deadline(String name, LocalDate date, LocalTime time) {
        super(name);
        this.time = time;
        this.date = date;
    }

    Deadline(String name, LocalDate date) {
        super(name);
        this.time = null;
        this.date = date;
    }

    Deadline(String name, String time, boolean done) {
        super(name);
        this.time = time;
    }

    public String appendFile() {
        String doneString = (done == true ? "1" : "0");
        return "DEADLINE" + " | " + doneString + " | " + this.name + " | " + this.time;
    }

    @Override
    public String toString() {
        String doneString = (super.done == true ? "✓" : "✗");
        String dateFormat = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[D]" + "[" + doneString + "] " + this.name + " (by: " + dateFormat
                + (this.time != null ? " " + this.time.format(DateTimeFormatter.ofPattern("HHmma")) + " " : "") + ")";
    }
}

