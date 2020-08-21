import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    final LocalDate atDate;
    final LocalTime atTime;

    Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description, TaskType.EVENT);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    public LocalDate getDate() {
        return atDate;
    }

    @Override
    public String toString() {
        String time = atTime == null
                ? ""
                : ", " + atTime.format(DateTimeFormatter.ofPattern("h.mm a"));
        return super.toString()
                + " (at: " + atDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"))
                + time + ")";
    }
}