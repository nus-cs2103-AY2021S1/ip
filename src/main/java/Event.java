import java.time.LocalDate;

public class Event extends Task {
    private final String eventTime;
    private LocalDate date = null;

    Event(String name, String eventTime) {
        super(name);
        this.eventTime = eventTime;
        try {
            date = LocalDate.parse(eventTime);
        }
        catch (Exception e) {
            date = null;
        }
    }

    @Override
    public String toString() {
        String status = String.format("[E][%s] ", (super.done ? "✓" : "✗"));
        String time = date == null
                ? String.format(" (at: %s)", eventTime)
                : " (at: " + DateParser.format(date) + ")";
        return status + this.getName() + time;
    }
}
