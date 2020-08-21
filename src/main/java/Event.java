import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    private String eventDate;

    public Event(String eventName, String eventDate) {
        super(eventName);
        this.eventDate = eventDate;
    }

    public Event(String eventName, LocalDate localDate) {
        super(eventName, true, localDate);
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (at: " + (super.hasDate()
                            ? localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                            : eventDate)
                         + ")";
    }

    @Override
    public String getSaveFormat() {
        return "E" + " | " +
                super.getSaveFormat() + " | " +
                (super.hasDate()
                        ? localDate
                        : eventDate);
    }
}
