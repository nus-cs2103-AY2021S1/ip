import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public String type;

    public Event(String desc, boolean isDone, LocalDate time) {
        super(desc, isDone, time);
        type = "E";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        String formattedDate = this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return super.toString() + " (at: " + formattedDate + ")";
    }
}
