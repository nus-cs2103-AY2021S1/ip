import java.time.LocalDateTime;

public class Event extends Task {

    private LocalDateTime date;

    public Event(String description, LocalDateTime date) {
        super(description);
        super.symbol = 'E';
        this.date = date;
    }

    public Event(String description, LocalDateTime date, boolean isCompleted) {
        super(description, isCompleted);
        super.symbol = 'E';
        this.date = date;
    }

    @Override
    public Event markCompleted() {
        return new Event(description, date, true);
    }

    @Override
    public String getStorageString() {
        String baseString = super.getStorageString();
        return String.format("%s | %s", baseString, date);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", symbol, super.toString(), date);
    }
}
