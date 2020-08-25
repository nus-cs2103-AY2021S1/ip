import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    
    private String timing;

    public Event (String desc) {
        super(desc.split("event ")[1].split(" /at ")[0], "E");
        this.timing = desc.split("event ")[1].split(" /at ")[1];
        try {
            String[] split = this.timing.split(" ");
            LocalDate date = LocalDate.parse(split[0]);
            String restOfTime = this.timing.split(split[0])[1];
            this.timing = date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + restOfTime;
        } catch (DateTimeParseException e) {
            // no date
        }
    }

    @Override
    public String toString () {
        return super.toString() + " (at: " + timing + ")";
    }

    public String getTiming() {
        return this.timing;
    }
}