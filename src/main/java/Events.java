import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Events extends Task {

    protected LocalDate scheduled;
    protected String date;

    public Events(String description, String scheduled) {
        super(description);
        this.date = scheduled;
        this.scheduled = LocalDate.parse(scheduled);
    }

    public Events(String description, String scheduled, boolean isDone) {
        super(description, isDone);
        this.date = scheduled;
        this.scheduled = LocalDate.parse(scheduled);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + scheduled.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String writeToFile() { return "E" + super.writeToFile() + " | " + date; }
}

