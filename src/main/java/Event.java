import java.time.LocalDate;

public class Event extends Task {

    public Event(String description, LocalDate time) {
        super(description, time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at:" + getDate() + ")";
    }

}
