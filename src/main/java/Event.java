import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    private LocalDate time;

    public Event(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        String month = time.getMonth().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"));
        int day = time.getDayOfMonth();
        int year = time.getYear();
        String timeDisplay = String.format("%d %s %d", day, month, year);
        return "[E]" + super.toString() + String.format(" (at: %s)", timeDisplay);
    }
}
