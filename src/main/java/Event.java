import java.time.LocalDateTime;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        if(at.getMinute() >= 10) {
            return "[E]" + super.toString() + " (at: " + at.getMonth().toString().substring(0, 3) + " " +
                    at.getDayOfMonth() + " " + at.getYear() + " " +
                    String.format("%d:%d)", at.getHour(), at.getMinute()) + ")";
        } else {
            return "[E]" + super.toString() + " (at: " + at.getMonth().toString().substring(0, 3) + " " +
                    at.getDayOfMonth() + " " + at.getYear() + " " + at.getHour() + ":0" + at.getMinute() + ")";
        }

    }
}
