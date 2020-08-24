import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate localDate;
    protected LocalTime localTime;

    Deadline(String description, String localDate, String localTime) {
        super(description);
        this.localDate = localDate != null ? LocalDate.parse(localDate) : null;
        this.localTime = localTime != null ? LocalTime.parse(localTime) : null;
    }

    public boolean hasDate() {
        return this.localDate != null;
    }

    public boolean hasTime() {
        return this.localTime != null;
    }

    @Override
    public String toString() {
        String toReturn = "[D]" + super.toString();
        if (this.hasDate()) {
            if (this.hasTime()) {
                toReturn += "(at: " + this.localDate.format((DateTimeFormatter.ofPattern("MMM dd yyyy")))
                                + " " + this.localTime + ")";
            } else {
                toReturn +="(at: " + this.localDate.format((DateTimeFormatter.ofPattern("MMM dd yyyy"))) + ")";
            }
        }
        return toReturn;
    }
}
