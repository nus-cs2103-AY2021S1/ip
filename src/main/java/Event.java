import java.time.LocalDateTime;

/**
 * Represents an event which will happen at a fixed time.
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Constructs an event with a deadline.
     * @param description the description of the event.
     * @param at the happening time of the event.
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        if(at.getMinute() >= 10) {
            return "[E]" + super.toString() + "(at: " + at.getMonth().toString().substring(0, 3) + " " +
                    at.getDayOfMonth() + " " + at.getYear() + " " +
                    String.format("%d:%d)", at.getHour(), at.getMinute()) + ")";
        } else {
            return "[E]" + super.toString() + "(at: " + at.getMonth().toString().substring(0, 3) + " " +
                    at.getDayOfMonth() + " " + at.getYear() + " " + at.getHour() + ":0" + at.getMinute() + ")";
        }

    }
}
