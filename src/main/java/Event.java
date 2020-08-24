import java.time.LocalDate;

public class Event extends Task {

    String by;

    public Event(String description, LocalDate date) {
        super(description, date);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.toString() + ")\n";
    }
}