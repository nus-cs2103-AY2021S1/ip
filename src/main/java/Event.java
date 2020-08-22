import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime dateTime;

    Event(String task, LocalDateTime dateTime) {
        super(task);
        this.dateTime = dateTime;
    }

    public String toString() {
        String MMM = this.dateTime.getMonth().toString().substring(0, 3);
        String DDD = this.dateTime.getDayOfWeek().toString().substring(0, 3);
        int dd = this.dateTime.getDayOfMonth();
        int yyyy = this.dateTime.getYear();
        return "[E]" + super.toString() + "(at: " + dd + " " + MMM + " " + yyyy + ", " + DDD + ")";
    }
}