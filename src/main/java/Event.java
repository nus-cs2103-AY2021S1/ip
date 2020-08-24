import java.time.LocalDateTime;

public class Event extends Task{

    public Event(String description, LocalDateTime dueDate) {
        super(description, dueDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at : " + super.getDateString() + ")";
    }
}
